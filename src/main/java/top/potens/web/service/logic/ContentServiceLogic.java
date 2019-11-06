package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.framework.util.StringUtil;
import top.potens.web.dao.db.auto.*;
import top.potens.web.model.*;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentServiceLogic
 * @projectName web-api
 * @date 2019/11/1 14:23
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentServiceLogic {
    private final ContentMapper contentMapper;
    private final ContentNewsMapper contentNewsMapper;
    private final ContentCommentMapper contentCommentMapper;
    private final ContentTopicMapper contentTopicMapper;
    private final ContentTopicSelectOptionMapper contentTopicSelectOptionMapper;

    /***
     * 插入新闻数据
     * @param content
     * @param contentNews
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer insertContentNews(Content content, ContentNews contentNews, List<ContentComment> contentCommentList) {
        AppLogger.info("开始插入新闻数据 content:[{}] contentNews:[{}]", JSON.toJSONString(content), JSON.toJSONString(contentNews));
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
            AppLogger.info("对应的内容存在", JSON.toJSONString(contents));
            contentId = contents.get(0).getContentId();
        } else {
            contentMapper.insertSelective(content);
            contentNews.setContentId(content.getContentId());
            contentNewsMapper.insertSelective(contentNews);
            AppLogger.info("插入成功 contentId", content.getContentId());
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

    @Transactional(rollbackFor = Exception.class)
    public void insertContentAndTopic(Content content, ContentTopic contentTopic, List<ContentTopicSelectOption> contentTopicSelectOptionList) {
        Date now = new Date();
        content.setCreateTime(now);
        content.setUpdateTime(now);
        content.setOutId(StringUtil.getUuid());
        contentMapper.insertSelective(content);
        contentTopic.setCreateTime(now);
        contentTopic.setUpdateTime(now);
        contentTopic.setContentId(content.getContentId());
        contentTopicMapper.insertSelective(contentTopic);
        if (CollectionUtils.isNotEmpty(contentTopicSelectOptionList)) {
            contentTopicSelectOptionList.forEach(contentTopicSelectOption -> {
                contentTopicSelectOption.setCreateTime(now);
                contentTopicSelectOption.setUpdateTime(now);
                contentTopicSelectOption.setContentId(content.getContentId());
                contentTopicSelectOption.setContentTopicId(contentTopic.getContentTopicId());
                contentTopicSelectOptionMapper.insertSelective(contentTopicSelectOption);
            });
        }
    }
}
