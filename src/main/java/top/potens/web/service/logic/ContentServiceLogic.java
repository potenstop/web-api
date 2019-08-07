package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.framework.serialization.JSON;
import top.potens.web.dao.db.auto.ContentCommentMapper;
import top.potens.web.dao.db.auto.ContentMapper;
import top.potens.web.dao.db.auto.ContentNewsMapper;
import top.potens.web.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2019/7/21.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentServiceLogic {
    private final ContentMapper contentMapper;
    private final ContentNewsMapper contentNewsMapper;
    private final ContentCommentMapper contentCommentMapper;

    /***
     * 插入新闻数据
     * @param content
     * @param contentNews
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer insertContentNews(Content content, ContentNews contentNews, List<ContentComment> contentCommentList) {
        AppUtil.info("开始插入新闻数据 content:[{}] contentNews:[{}]", JSON.toJSONString(content), JSON.toJSONString(contentNews));
        Date now = new Date();
        content.setCreateTime(now);
        content.setUpdateTime(now);
        contentNews.setCreateTime(now);
        contentNews.setUpdateTime(now);
        ContentExample contentExample = new ContentExample();
        contentExample.createCriteria().andOutIdEqualTo(content.getOutId()).andChannelIdEqualTo(content.getChannelId());
        List<Content> contents = contentMapper.selectByExample(contentExample);
        Integer contentId;
        if (contents.size() > 0) {
            AppUtil.info("对应的内容存在", JSON.toJSONString(contents));
            contentId = contents.get(0).getContentId();
        } else {
            contentMapper.insertSelective(content);
            contentNews.setContentId(content.getContentId());
            contentNewsMapper.insertSelective(contentNews);
            AppUtil.info("插入成功 contentId", content.getContentId());
            contentId = content.getContentId();
        }
        // 增加评论 存在则不操作
        contentCommentList.forEach(contentComment -> {
            contentComment.setContentId(contentId);
            ContentCommentExample contentCommentExample = new ContentCommentExample();
            contentCommentExample.createCriteria().andContentIdEqualTo(contentId).andOutCommentIdEqualTo(contentComment.getOutCommentId());
            List<ContentComment> contentComments = contentCommentMapper.selectByExample(contentCommentExample);
            if (CollectionUtils.isEmpty(contentComments)) {
                contentCommentMapper.insertSelective(contentComment);
            }
        });
        return contentId;
    }
}
