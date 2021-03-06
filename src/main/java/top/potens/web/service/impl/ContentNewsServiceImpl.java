package top.potens.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.annotation.Lock;
import top.potens.framework.enums.LockModel;
import top.potens.framework.log.AppLogger;
import top.potens.framework.model.PageResponse;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.DateUtil;
import top.potens.framework.util.StringUtil;
import top.potens.web.bmo.ContentNewsSelectSimpleListBo;
import top.potens.web.bmo.UserSignAuthBo;
import top.potens.web.common.constant.ContentConstant;
import top.potens.web.common.constant.LockConstant;
import top.potens.web.dao.db.auto.ContentMapper;
import top.potens.web.dao.db.auto.ContentNewsMapper;
import top.potens.web.dao.db.ext.ContentLabelExMapper;
import top.potens.web.dao.db.ext.ContentNewsExMapper;
import top.potens.web.model.*;
import top.potens.web.request.ContentCommentOutRequest;
import top.potens.web.request.ContentNewsListRequest;
import top.potens.web.request.ContentNewsOutRequest;
import top.potens.web.request.UserOutRequest;
import top.potens.web.response.ContentNewItemResponse;
import top.potens.web.service.ChannelService;
import top.potens.web.service.ContentNewsService;
import top.potens.web.service.ContentZoneService;
import top.potens.web.service.UserService;
import top.potens.web.service.logic.CacheServiceLogic;
import top.potens.web.service.logic.ContentServiceLogic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentNewsServiceImpl
 * @projectName web-api
 * @date 2019/6/27 17:56
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentNewsServiceImpl extends AbstractSimpleTableCommonServiceImpl<ContentNews> implements ContentNewsService {
    private final CacheServiceLogic cacheServiceLogic;
    private final ContentServiceLogic contentServiceLogic;
    private final ContentLabelExMapper contentLabelExMapper;
    private final UserService userService;
    private final ContentNewsExMapper contentNewsExMapper;
    private final ContentNewsMapper contentNewsMapper;
    private final ContentMapper contentMapper;
    private final ChannelService channelService;
    private final ContentZoneService contentZoneService;

    @Override
    protected ContentNews mapperByPrimaryKey(Integer id) {
        return contentNewsMapper.selectByPrimaryKey(id);
    }

    @Override
    protected ContentNews mapperBySecondPrimaryKey(Integer id) {
        ContentNewsExample contentNewsExample = new ContentNewsExample();
        contentNewsExample.createCriteria().andContentIdEqualTo(id);
        List<ContentNews> contentNews = contentNewsMapper.selectByExample(contentNewsExample);
        if (contentNews.size() != 1) {
            return null;
        }
        return contentNews.get(0);
    }

    @Override
    protected Boolean isDelete(ContentNews contentNews) {
        return false;
    }

    @Override
    @Lock(lockModel = LockModel.FAIR, keys = LockConstant.CONTENT_NEWS_OUT_KEY + "#{#request.webSource +':' + #request.id}", attemptTimeout = 10, lockWatchTimeout = 120)
    public void outPush(ContentNewsOutRequest request) {
        Channel channel = cacheServiceLogic.getChannelByCode(request.getWebSource());
        ContentZone contentZone = cacheServiceLogic.getContentZoneByCode(request.getZone());
        Content content = new Content();
        content.setOutId(request.getId());
        content.setChannelId(channel.getChannelId());
        content.setOutUrl(request.getUrl());
        content.setState(ContentConstant.ContentState.ONLINE);

        ContentNews contentNews = new ContentNews();
        contentNews.setArticleSource(request.getArticleSource());
        contentNews.setTitle(request.getTitle());
        contentNews.setEditor(request.getEditor());
        contentNews.setPublishTime(DateUtil.getLocalDateTime(request.getTime()));
        contentNews.setContentZoneId(contentZone.getContentZoneId());
        contentNews.setToken(StringUtil.getUuid());
        contentNews.setArticle(request.getHtml());
        // 插入用户
        List<UserOutRequest> userOutRequests = request.getCommentList().stream().map(ContentCommentOutRequest::getUser).collect(Collectors.toList());
        List<UserSignAuthBo> userSignAuthBos = userService.insertByOutList(userOutRequests, channel.getChannelId());
        Map<String, Integer> userMap = new HashMap<>();
        userSignAuthBos.forEach(userSignAuthBo -> {
            userMap.put(userSignAuthBo.getUserAuth().getIdentifier(), userSignAuthBo.getUserId());
        });
        List<ContentComment> contentComments = new ArrayList<>();
        request.getCommentList().forEach(commentRequest -> {
            ContentComment contentComment = new ContentComment();
            contentComment.setAgainst(commentRequest.getAgainst());
            contentComment.setOutCommentId(commentRequest.getId());
            contentComment.setContent(commentRequest.getContent());
            contentComment.setUserId(userMap.get(commentRequest.getUser().getSourceUserId()));
            contentComment.setVote(commentRequest.getVote());
            contentComment.setShare(commentRequest.getShare());
            contentComment.setCreateTime(commentRequest.getTime());
            contentComment.setUpdateTime(commentRequest.getTime());
            contentComments.add(contentComment);
        });
        Integer contentId = contentServiceLogic.insertContentNews(content, contentNews, contentComments);
        AppLogger.info("数据插入完成 contentId:[{}]", contentId);
        // 更新标签
        if (request.getLabels() != null) {
            request.getLabels().forEach(labelName -> {
                Label labelByName = cacheServiceLogic.getLabelByName(labelName);
                ContentLabel contentLabel = new ContentLabel();
                contentLabel.setContentId(contentId);
                contentLabel.setLabelId(labelByName.getLabelId());
                contentLabelExMapper.insertOrUpdate(contentLabel);
            });
        }
    }

    @Override
    public PageResponse<ContentNewItemResponse> list(ContentNewsListRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        ContentNewsSelectSimpleListBo contentNewsSelectSimpleListBo = new ContentNewsSelectSimpleListBo();
        contentNewsSelectSimpleListBo.setContentId(request.getContentId());
        DateUtil.setScopeDate(request.getCreateTime(), contentNewsSelectSimpleListBo::setCreateTimeStart, contentNewsSelectSimpleListBo::setCreateTimeEnd);
        DateUtil.setScopeDate(request.getUpdateTime(), contentNewsSelectSimpleListBo::setUpdateTimeStart, contentNewsSelectSimpleListBo::setUpdateTimeEnd);
        DateUtil.setScopeDate(request.getPublishTime(), contentNewsSelectSimpleListBo::setPublishTimeStart, contentNewsSelectSimpleListBo::setPublishTimeEnd);
        if (StringUtil.isNotBlank(request.getOrderBy())) {
            contentNewsSelectSimpleListBo.setOrderBy(request.getOrderBy());
        } else {
            contentNewsSelectSimpleListBo.setOrderBy("content_id desc");
        }
        contentNewsSelectSimpleListBo.setState(request.getState());
        List<Integer> contentIdLIst = contentNewsExMapper.selectSimpleList(contentNewsSelectSimpleListBo);
        PageSerializable<Integer> pageSerializable = PageSerializable.of(contentIdLIst);
        PageResponse<ContentNewItemResponse> contentNewItemResponsePageResponse = new PageResponse<>();
        contentNewItemResponsePageResponse.setTotal(pageSerializable.getTotal());
        List<ContentNewItemResponse> contentNewItemResponses = new ArrayList<>();
        if (contentIdLIst.size() == 0) {
            contentNewItemResponsePageResponse.setList(contentNewItemResponses);
            return contentNewItemResponsePageResponse;
        }

        Set<Integer> channelIdSet = new HashSet<>();
        Set<Integer> contentZoneIdSet = new HashSet<>();
        // 查询content
        ContentExample contentExample = new ContentExample();
        contentExample.createCriteria().andContentIdIn(contentIdLIst);
        Map<Integer, Content> contentMap = contentMapper.selectByExample(contentExample).stream().collect(Collectors.toMap(Content::getContentId, content -> {
            channelIdSet.add(content.getChannelId());
            return content;
        }, (k1, k2) -> k1));

        // 查询contentNew
        ContentNewsExample contentNewsExample = new ContentNewsExample();
        contentNewsExample.createCriteria().andContentIdIn(contentIdLIst);
        Map<Integer, ContentNews> contentNewsMap = contentNewsMapper.selectByExampleWithBLOBs(contentNewsExample).stream().collect(Collectors.toMap(ContentNews::getContentId, contentNews -> {
            contentZoneIdSet.add(contentNews.getContentZoneId());
            return contentNews;
        }, (k1, k2) -> k1));

        // 查询channel
        Map<Integer, Channel> channelMap = channelService.selectIdList(new ArrayList<>(channelIdSet));

        // 查询zone
        Map<Integer, ContentZone> contentZoneMap = contentZoneService.selectIdList(new ArrayList<>(contentZoneIdSet));

        contentIdLIst.forEach(contentId -> {
            if (contentMap.containsKey(contentId) && contentNewsMap.containsKey(contentId)) {
                ContentNewItemResponse contentNewItemResponse = new ContentNewItemResponse();
                Content content = contentMap.get(contentId);
                ContentNews contentNews = contentNewsMap.get(contentId);
                BeanCopierUtil.convert(content, contentNewItemResponse);
                BeanCopierUtil.convert(contentNews, contentNewItemResponse);
                if (channelMap.containsKey(content.getChannelId())) {
                    contentNewItemResponse.setChannelName(channelMap.get(content.getChannelId()).getChannelName());
                }
                if (contentZoneMap.containsKey(contentNews.getContentZoneId())) {
                    contentNewItemResponse.setContentZoneName(contentZoneMap.get(contentNews.getContentZoneId()).getZoneName());
                }
                contentNewItemResponses.add(contentNewItemResponse);
            }
        });

        contentNewItemResponsePageResponse.setList(contentNewItemResponses);
        return contentNewItemResponsePageResponse;
    }
}
