package top.potens.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.annotation.Lock;
import top.potens.framework.enums.LockModel;
import top.potens.framework.exception.ApiException;
import top.potens.framework.model.DateScope;
import top.potens.framework.model.PageResponse;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.DateUtil;
import top.potens.web.bmo.ContentTopicCompleteBo;
import top.potens.web.code.ContentCode;
import top.potens.web.common.constant.ChannelConstant;
import top.potens.web.common.constant.ContentConstant;
import top.potens.web.common.constant.ContentTopicConstant;
import top.potens.web.common.constant.LockConstant;
import top.potens.web.dao.db.auto.ContentTopicMapper;
import top.potens.web.dao.db.auto.ContentTopicSelectOptionMapper;
import top.potens.web.dao.db.ext.ContentTopicExMapper;
import top.potens.web.model.*;
import top.potens.web.request.*;
import top.potens.web.response.ContentTopicListItemResponse;
import top.potens.web.response.ContentTopicSelectOptionResponse;
import top.potens.web.response.ContentTopicViewResponse;
import top.potens.web.service.ContentService;
import top.potens.web.service.ContentTopicService;
import top.potens.web.service.logic.CacheServiceLogic;
import top.potens.web.service.logic.ContentServiceLogic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicServiceImpl
 * @projectName web-api
 * @date 2019/11/6 11:32
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentTopicServiceImpl extends AbstractSimpleTableCommonServiceImpl<ContentTopic> implements ContentTopicService {
    private final ContentTopicMapper contentTopicMapper;
    private final ContentTopicExMapper contentTopicExMapper;
    private final ContentService contentService;
    private final CacheServiceLogic cacheServiceLogic;
    private final ContentServiceLogic contentServiceLogic;
    private final ContentTopicSelectOptionMapper contentTopicSelectOptionMapper;

    @Override
    protected ContentTopic mapperByPrimaryKey(Integer id) {
        return contentTopicMapper.selectByPrimaryKey(id);
    }

    @Override
    protected ContentTopic mapperBySecondPrimaryKey(Integer id) {
        ContentTopicExample example = new ContentTopicExample();
        example.createCriteria().andContentIdEqualTo(id);
        List<ContentTopic> contentTopics = contentTopicMapper.selectByExample(example);
        if (contentTopics.size() != 1) {
            return null;
        }
        return contentTopics.get(0);
    }

    @Override
    protected Boolean isDelete(ContentTopic contentTopic) {
        return false;
    }
    @Override
    public List<ContentTopic> byIdList(List<Integer> contentIdList) {
        if (CollectionUtils.isEmpty(contentIdList)) {
            return new ArrayList<>();
        }
        ContentTopicExample example = new ContentTopicExample();
        example.createCriteria().andContentIdIn(contentIdList);
        return contentTopicMapper.selectByExample(example);
    }

    @Override
    public Map<Integer, ContentTopic> byIdListResultMap(List<Integer> contentIdList) {
        List<ContentTopic> contentTopics = byIdList(contentIdList);
        return contentTopics.stream().collect(Collectors.toMap(ContentTopic::getContentId, contentTopic -> contentTopic));
    }

    @Override
    public PageResponse<ContentTopicListItemResponse> selectList(ContentTopicListItemRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageResponse<ContentTopicListItemResponse> response = new PageResponse<>();
        DateScope dateScope = DateUtil.getDateScope(request.getCreateTime());
        String title = request.getTitle();
        if (title != null) {
            title = "%" + title + "%";
        }
        List<Integer> idList = contentTopicExMapper.selectIdListByFilter(request.getContentId(), dateScope.getStartDate(),
                dateScope.getEndDate(), title, request.getState(), request.getOrderBy(), request.getContentIdList());
        Map<Integer, Content> contentMap = contentService.byIdListResultMap(idList);
        Map<Integer, ContentTopic> contentTopicMap = byIdListResultMap(idList);
        List<ContentTopicListItemResponse> contentTopicListItemResponses = new ArrayList<>();
        idList.forEach(id -> {
            if (contentMap.containsKey(id) && contentTopicMap.containsKey(id)) {
                Content content = contentMap.get(id);
                ContentTopic contentTopic = contentTopicMap.get(id);
                ContentTopicListItemResponse itemResponse = BeanCopierUtil.convert(content, ContentTopicListItemResponse.class);
                BeanCopierUtil.convert(contentTopic, itemResponse);
                contentTopicListItemResponses.add(itemResponse);
            }
        });
        PageSerializable<Integer> of = PageSerializable.of(idList);
        response.setTotal(of.getTotal());
        response.setList(contentTopicListItemResponses);
        return response;
    }

    private void insertCheck(ContentTopicAddRequest request) {
        // 验证题目类型
        if (!ContentTopicConstant.TopicType.ALL_SET.contains(request.getTopicType())) {
            throw new ApiException(ContentCode.CONTENT_TOPIC_TYPE_ERROR);
        }
        // 验证状态
        if (!ContentConstant.ContentState.ONLINE.equals(request.getState()) && !ContentConstant.ContentState.OFFLINE.equals(request.getState())) {
            throw new ApiException(ContentCode.CONTENT_STATE_ERROR);
        }
        // 验证选择题有没有选项
        if (ContentTopicConstant.TopicType.SIGN_SELECT.equals(request.getTopicType()) || ContentTopicConstant.TopicType.MUL_SELECT.equals(request.getTopicType())) {
            if (CollectionUtils.isEmpty(request.getAddOptionList())) {
                throw new ApiException(ContentCode.CONTENT_SELECT_NOT_OPTION);
            }
        }
    }
    private Content insertAssembleContent(ContentTopicAddRequest request) {
        Channel channel = cacheServiceLogic.getChannelByCode(ChannelConstant.ChannelCode.SELF_INSERT);
        Content content = new Content();
        content.setChannelId(channel.getChannelId());
        content.setState(request.getState());
        content.setContentType(ContentConstant.ContentType.TOPIC);
        return content;
    }
    private ContentTopic insertAssembleContentTopic(ContentTopicAddRequest request) {
        ContentTopic contentTopic = new ContentTopic();
        contentTopic.setTitle(request.getTitle());
        contentTopic.setAnalysis(request.getAnalysis());
        contentTopic.setAnswer(request.getAnswer());
        contentTopic.setTopicType(request.getTopicType());
        return contentTopic;
    }
    private List<ContentTopicSelectOption> insertAssembleContentTopicSelectOption(ContentTopicAddRequest request) {
        List<ContentTopicSelectOption> contentTopicSelectOptionList = new ArrayList<>();
        if (ContentTopicConstant.TopicType.SELECT_SET.contains(request.getTopicType())) {
            request.getAddOptionList().forEach(option -> {
                ContentTopicSelectOption contentTopicSelectOption = new ContentTopicSelectOption();
                contentTopicSelectOption.setOptionLabel(option.getOptionLabel());
                contentTopicSelectOption.setIsOptionAnswer(option.getIsOptionAnswer());
                contentTopicSelectOptionList.add(contentTopicSelectOption);
            });
        }
        return contentTopicSelectOptionList;
    }
    @Override
    public Integer insertOne(ContentTopicAddRequest request) {
        insertCheck(request);
        // 组装数据
        Content content = insertAssembleContent(request);
        ContentTopic contentTopic = insertAssembleContentTopic(request);
        List<ContentTopicSelectOption> contentTopicSelectOptionList = insertAssembleContentTopicSelectOption(request);
        // 入库
        contentServiceLogic.insertContentAndTopic(content, contentTopic, contentTopicSelectOptionList);
        return content.getContentId();
    }

    @Override
    public ContentTopicViewResponse selectById(Integer contentId) {
        Content content = contentService.byPrimaryKey(contentId);
        ContentTopic contentTopic = byPrimaryKeyException(content.getContentId());

        ContentTopicViewResponse contentTopicViewResponse = BeanCopierUtil.convert(content, ContentTopicViewResponse.class);
        BeanCopierUtil.convert(contentTopic, contentTopicViewResponse);

        if (ContentTopicConstant.TopicType.SELECT_SET.contains(contentTopic.getTopicType())) {
            ContentTopicSelectOptionExample contentTopicSelectOptionExample = new ContentTopicSelectOptionExample();
            contentTopicSelectOptionExample.createCriteria().andContentIdEqualTo(content.getContentId());
            List<ContentTopicSelectOption> contentTopicSelectOptions = contentTopicSelectOptionMapper.selectByExample(contentTopicSelectOptionExample);
            List<ContentTopicSelectOptionResponse> contentTopicSelectOptionResponseList = BeanCopierUtil.convert(contentTopicSelectOptions, ContentTopicSelectOptionResponse.class);
            contentTopicViewResponse.setAddOptionList(contentTopicSelectOptionResponseList);
        } else {
            contentTopicViewResponse.setAddOptionList(new ArrayList<>());
        }
        return contentTopicViewResponse;
    }
    @Override
    public List<ContentTopicViewResponse> selectByIdList(List<Integer> contentIdList) {
        List<Content> contentList = contentService.byIdList(contentIdList);
        Map<Integer, ContentTopic> integerContentTopicMap = byIdListResultMap(contentIdList);
        Map<Integer, List<ContentTopicSelectOption>> contentTopicSelectOptionMap = new HashMap<>();
        List<ContentTopicViewResponse> contentTopicViewResponseList = new ArrayList<>();
        // 找出选择题
        List<Integer> selectIdList = contentList.stream().filter(content -> ContentTopicConstant.TopicType.SELECT_SET.contains(content.getContentType())).map(Content::getContentId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(selectIdList)) {
            ContentTopicSelectOptionExample contentTopicSelectOptionExample = new ContentTopicSelectOptionExample();
            contentTopicSelectOptionExample.createCriteria().andContentIdIn(selectIdList);
            contentTopicSelectOptionMap = contentTopicSelectOptionMapper.selectByExample(contentTopicSelectOptionExample).stream().collect(Collectors.groupingBy(ContentTopicSelectOption::getContentId));
        }
        for (Content content : contentList) {
            ContentTopicViewResponse contentTopicViewResponse = BeanCopierUtil.convert(content, ContentTopicViewResponse.class);
            if (integerContentTopicMap.containsKey(content.getContentId())) {
                BeanCopierUtil.convert(integerContentTopicMap.get(content.getContentId()), contentTopicViewResponse);
            }
            if (contentTopicSelectOptionMap.containsKey(content.getContentId())) {
                List<ContentTopicSelectOptionResponse> contentTopicSelectOptionResponseList = BeanCopierUtil.convert(contentTopicSelectOptionMap.get(content.getContentId()), ContentTopicSelectOptionResponse.class);
                contentTopicViewResponse.setAddOptionList(contentTopicSelectOptionResponseList);
            }
            contentTopicViewResponseList.add(contentTopicViewResponse);
        }
        return contentTopicViewResponseList;
    }

    private void checkOptionIdNotFoundByOptionList(List<ContentTopicSelectOptionRequest> optionList) {
        if (CollectionUtils.isNotEmpty(optionList)) {
            optionList.forEach(item -> {
                if (item.getContentTopicSelectOptionId() == null) {
                    throw new ApiException(ContentCode.CONTENT_SELECT_OPTION_ID_NOT_PARAMS);
                }
            });
        }
    }

    @Override
    @Lock(lockModel = LockModel.FAIR, keys = LockConstant.CONTENT_TOPIC_UPDATE + "#{#request.contentId}", attemptTimeout = 10, lockWatchTimeout = 120)
    public Integer updateById(ContentTopicUpdateRequest request) {
        // 判断id是否存在
        ContentTopicViewResponse contentTopicViewResponse = selectById(request.getContentId());
        // 验证题目类型
        if (!ContentTopicConstant.TopicType.ALL_SET.contains(request.getTopicType())) {
            throw new ApiException(ContentCode.CONTENT_TOPIC_TYPE_ERROR);
        }

        // 判断删除和修改的必填id
        checkOptionIdNotFoundByOptionList(request.getModifyOptionList());
        // 验证状态
        if (!ContentConstant.ContentState.ONLINE.equals(request.getState()) && !ContentConstant.ContentState.OFFLINE.equals(request.getState())) {
            throw new ApiException(ContentCode.CONTENT_STATE_ERROR);
        }



        Content updateContent = new Content();
        ContentTopic updateContentTopic = new ContentTopic();

        updateContent.setState(request.getState());
        updateContent.setContentId(contentTopicViewResponse.getContentId());

        updateContentTopic.setContentTopicId(contentTopicViewResponse.getContentTopicId());
        updateContentTopic.setTitle(request.getTitle());
        updateContentTopic.setAnalysis(request.getAnalysis());
        updateContentTopic.setAnswer(request.getAnswer());
        updateContentTopic.setTopicType(request.getTopicType());
        // 选择题处理
        List<ContentTopicSelectOption> addContentTopicSelectOptionList = new ArrayList<>();
        List<Integer> removeContentTopicSelectOptionIdList = new ArrayList<>();
        List<ContentTopicSelectOption> modifyContentTopicSelectOptionList = new ArrayList<>();
        if (contentTopicViewResponse.getTopicType().equals(request.getTopicType())) {
            // 修改了类型
            if (ContentTopicConstant.TopicType.SELECT_SET.contains(contentTopicViewResponse.getTopicType()) && ContentTopicConstant.TopicType.NOT_SELECT_SET.contains(request.getTopicType())) {
                // 从选项题改为非选择题
                contentTopicViewResponse.getAddOptionList().forEach(item -> {
                    removeContentTopicSelectOptionIdList.add(item.getContentTopicSelectOptionId());
                });
            } else if (ContentTopicConstant.TopicType.SELECT_SET.contains(request.getTopicType()) && ContentTopicConstant.TopicType.NOT_SELECT_SET.contains(contentTopicViewResponse.getTopicType())) {
                // 从非选项题改为选择题

            }
        }
        if (ContentTopicConstant.TopicType.SELECT_SET.contains(request.getTopicType())) {
            Map<Integer, ContentTopicSelectOptionResponse> contentTopicSelectOptionResponseMap = contentTopicViewResponse.getAddOptionList().stream().collect(Collectors.toMap(ContentTopicSelectOptionResponse::getContentTopicSelectOptionId, v -> v));
            Date now = new Date();
            if (CollectionUtils.isNotEmpty(request.getAddOptionList())) {
                request.getAddOptionList().forEach(item -> {
                    ContentTopicSelectOption contentTopicSelectOption = new ContentTopicSelectOption();
                    contentTopicSelectOption.setIsOptionAnswer(item.getIsOptionAnswer());
                    contentTopicSelectOption.setOptionLabel(item.getOptionLabel());
                    contentTopicSelectOption.setContentId(contentTopicViewResponse.getContentId());
                    contentTopicSelectOption.setContentTopicId(contentTopicViewResponse.getContentTopicId());
                    contentTopicSelectOption.setCreateTime(now);
                    contentTopicSelectOption.setUpdateTime(now);
                    addContentTopicSelectOptionList.add(contentTopicSelectOption);
                });
            }
            if (CollectionUtils.isNotEmpty(request.getRemoveOptionIdList())) {
                request.getRemoveOptionIdList().forEach(item -> {
                    if (!contentTopicSelectOptionResponseMap.containsKey(item)) {
                        throw new ApiException(ContentCode.CONTENT_SELECT_OPTION_ID_NOT_FOUND);
                    }
                    removeContentTopicSelectOptionIdList.add(item);
                });
            }
            if (CollectionUtils.isNotEmpty(request.getModifyOptionList())) {
                request.getModifyOptionList().forEach(item -> {
                    if (!contentTopicSelectOptionResponseMap.containsKey(item.getContentTopicSelectOptionId())) {
                        throw new ApiException(ContentCode.CONTENT_SELECT_OPTION_ID_NOT_FOUND);
                    }
                    ContentTopicSelectOption contentTopicSelectOption = new ContentTopicSelectOption();
                    contentTopicSelectOption.setContentTopicSelectOptionId(item.getContentTopicSelectOptionId());
                    contentTopicSelectOption.setOptionLabel(item.getOptionLabel());
                    contentTopicSelectOption.setIsOptionAnswer(item.getIsOptionAnswer());
                    modifyContentTopicSelectOptionList.add(contentTopicSelectOption);
                });
            }
        }

        // 入库
        contentServiceLogic.updateContentAndTopic(updateContent, updateContentTopic, addContentTopicSelectOptionList, removeContentTopicSelectOptionIdList, modifyContentTopicSelectOptionList);
        return updateContent.getContentId();
    }

    @Override
    public Integer insertMultiple(ContentTopicMulAddRequest request) {
        List<ContentTopicAddRequest> contentTopicAddRequestList = request.getContentTopicAddRequestList();
        List<ContentTopicCompleteBo> contentTopicCompleteBoList = new ArrayList<>();
        contentTopicAddRequestList.forEach(contentTopicAddRequest -> {
            insertCheck(contentTopicAddRequest);
            ContentTopicCompleteBo contentTopicCompleteBo = new ContentTopicCompleteBo();
            // 组装数据
            Content content = insertAssembleContent(contentTopicAddRequest);
            ContentTopic contentTopic = insertAssembleContentTopic(contentTopicAddRequest);
            List<ContentTopicSelectOption> contentTopicSelectOptionList = insertAssembleContentTopicSelectOption(contentTopicAddRequest);
            contentTopicCompleteBo.setContent(content);
            contentTopicCompleteBo.setContentTopic(contentTopic);
            contentTopicCompleteBo.setContentTopicSelectOptionList(contentTopicSelectOptionList);
            contentTopicCompleteBoList.add(contentTopicCompleteBo);
        });
        contentServiceLogic.insertContentAndTopicMul(contentTopicCompleteBoList);
        return 1;
    }
}
