package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.log.AppUtil;
import top.potens.framework.util.DateUtil;
import top.potens.framework.util.StringUtil;
import top.potens.web.common.constant.ContentConstant;
import top.potens.web.dao.db.ext.ContentLabelExMapper;
import top.potens.web.model.*;
import top.potens.web.request.ContentNewsOutRequest;
import top.potens.web.service.ContentNewsService;
import top.potens.web.service.logic.ContentCacheService;
import top.potens.web.service.logic.ContentTransactionService;

/**
 * Created by wenshao on 2019/7/20.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentNewsImpl implements ContentNewsService {
    private final ContentCacheService contentCacheService;
    private final ContentTransactionService contentTransactionService;
    private final ContentLabelExMapper contentLabelExMapper;



    @Override
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
        contentNews.setPublishTime(DateUtil.getLocalDate(request.getTime()));
        contentNews.setContentZoneId(contentZone.getContentZoneId());
        contentNews.setToken(StringUtil.getUuid());

        Integer contentId = contentTransactionService.insertContentNews(content, contentNews);
        AppUtil.info("数据插入完成 contentId:[{}]", contentId);
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
        // 更新评论

    }
}
