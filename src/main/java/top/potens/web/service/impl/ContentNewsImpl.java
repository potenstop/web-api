package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.annotation.Lock;
import top.potens.framework.enums.LockModel;
import top.potens.framework.log.AppLogger;
import top.potens.framework.util.DateUtil;
import top.potens.framework.util.StringUtil;
import top.potens.web.bmo.UserSignAuthBo;
import top.potens.web.common.constant.ContentConstant;
import top.potens.web.common.constant.LockConstant;
import top.potens.web.dao.db.ext.ContentLabelExMapper;
import top.potens.web.model.*;
import top.potens.web.request.ContentCommentOutRequest;
import top.potens.web.request.ContentNewsOutRequest;
import top.potens.web.request.UserOutRequest;
import top.potens.web.service.ContentNewsService;
import top.potens.web.service.UserService;
import top.potens.web.service.logic.ContentCacheService;
import top.potens.web.service.logic.ContentServiceLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by wenshao on 2019/7/20.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentNewsImpl implements ContentNewsService {
    private final ContentCacheService contentCacheService;
    private final ContentServiceLogic contentServiceLogic;
    private final ContentLabelExMapper contentLabelExMapper;
    private final UserService userService;

    @Override
    @Lock(lockModel = LockModel.FAIR, keys = LockConstant.CONTENT_NEWS_OUT_KEY + "#{#request.webSource +':' + #request.id}", attemptTimeout = 10, lockWatchTimeout = 120)
    public void outPush(ContentNewsOutRequest request) {
        Channel channel = contentCacheService.getChannelByCode(request.getWebSource());
        ContentZone contentZone = contentCacheService.getContentZoneByCode(request.getZone());
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
                Label labelByName = contentCacheService.getLabelByName(labelName);
                ContentLabel contentLabel = new ContentLabel();
                contentLabel.setContentId(contentId);
                contentLabel.setLabelId(labelByName.getLabelId());
                contentLabelExMapper.insertOrUpdate(contentLabel);
            });
        }
    }
}
