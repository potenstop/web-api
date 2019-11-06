package top.potens.web.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.model.DateScope;
import top.potens.framework.model.PageResponse;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.DateUtil;
import top.potens.web.code.ContentCode;
import top.potens.web.common.constant.ChannelConstant;
import top.potens.web.common.constant.ContentConstant;
import top.potens.web.common.constant.ContentTopicConstant;
import top.potens.web.dao.db.auto.ContentTopicMapper;
import top.potens.web.dao.db.auto.ContentTopicSelectOptionMapper;
import top.potens.web.dao.db.ext.ContentTopicExMapper;
import top.potens.web.model.*;
import top.potens.web.request.ContentTopicAddRequest;
import top.potens.web.request.ContentTopicListItemRequest;
import top.potens.web.request.ContentTopicUpdateRequest;
import top.potens.web.response.ContentTopicListItemResponse;
import top.potens.web.response.ContentTopicSelectOptionResponse;
import top.potens.web.response.ContentTopicViewResponse;
import top.potens.web.service.ContentService;
import top.potens.web.service.ContentTopicService;
import top.potens.web.service.logic.CacheServiceLogic;
import top.potens.web.service.logic.ContentServiceLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ContentTopicServiceImpl implements ContentTopicService {
    private final ContentTopicMapper contentTopicMapper;
    private final ContentTopicExMapper contentTopicExMapper;
    private final ContentService contentService;
    private final CacheServiceLogic cacheServiceLogic;
    private final ContentServiceLogic contentServiceLogic;
    private final ContentTopicSelectOptionMapper contentTopicSelectOptionMapper;

    @Override
    public ContentTopic byId(Integer contentId) {
        if (contentId == null) {
            throw new ApiException(ContentCode.CONTENT_ID_NOT_FOUND);
        }
        ContentTopicExample example = new ContentTopicExample();
        example.createCriteria().andContentIdEqualTo(contentId);
        List<ContentTopic> contentTopicList = contentTopicMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(contentTopicList)) {
            throw new ApiException(ContentCode.CONTENT_ID_NOT_FOUND);
        }
        return contentTopicList.get(0);
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
        List<Integer> idList = contentTopicExMapper.selectIdListByFilter(request.getContentId(), dateScope.getStartDate(), dateScope.getEndDate(), title, request.getState(), request.getOrderBy());
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
        response.setList(contentTopicListItemResponses);
        return response;
    }

    @Override
    public Integer insertOne(ContentTopicAddRequest request) {
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
        Channel channel = cacheServiceLogic.getChannelByCode(ChannelConstant.ChannelCode.SELF_INSERT);
        // 组装数据
        Content content = new Content();
        ContentTopic contentTopic = new ContentTopic();
        content.setChannelId(channel.getChannelId());
        content.setState(request.getState());
        content.setContentType(ContentConstant.ContentType.TOPIC);

        contentTopic.setTitle(request.getTitle());
        contentTopic.setAnalysis(request.getAnalysis());
        contentTopic.setAnswer(request.getAnswer());
        contentTopic.setTopicType(request.getTopicType());
        List<ContentTopicSelectOption> contentTopicSelectOptionList = new ArrayList<>();
        if (ContentTopicConstant.TopicType.SIGN_SELECT.equals(request.getTopicType()) || ContentTopicConstant.TopicType.MUL_SELECT.equals(request.getTopicType())) {
            request.getAddOptionList().forEach(option -> {
                ContentTopicSelectOption contentTopicSelectOption = new ContentTopicSelectOption();
                contentTopicSelectOption.setOptionLabel(option.getOptionLabel());
                contentTopicSelectOption.setIsOptionAnswer(option.getIsOptionAnswer());
                contentTopicSelectOptionList.add(contentTopicSelectOption);
            });
        }
        // 入库
        contentServiceLogic.insertContentAndTopic(content, contentTopic, contentTopicSelectOptionList);
        return content.getContentId();
    }

    @Override
    public ContentTopicViewResponse viewById(Integer contentId) {
        Content content = contentService.byId(contentId);
        ContentTopic contentTopic = byId(content.getContentId());

        ContentTopicViewResponse contentTopicViewResponse = BeanCopierUtil.convert(content, ContentTopicViewResponse.class);
        BeanCopierUtil.convert(contentTopic, contentTopicViewResponse);

        if (ContentTopicConstant.TopicType.SIGN_SELECT.equals(contentTopic.getTopicType()) || ContentTopicConstant.TopicType.MUL_SELECT.equals(contentTopic.getTopicType())) {
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
    public Integer updateById(ContentTopicUpdateRequest request) {
        return null;
    }
}
