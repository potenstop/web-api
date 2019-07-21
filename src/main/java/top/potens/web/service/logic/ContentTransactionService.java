package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.potens.framework.log.AppUtil;
import top.potens.framework.serialization.JSON;
import top.potens.web.dao.db.auto.ContentMapper;
import top.potens.web.dao.db.auto.ContentNewsMapper;
import top.potens.web.model.Content;
import top.potens.web.model.ContentExample;
import top.potens.web.model.ContentNews;

import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2019/7/21.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentTransactionService {
    private final ContentMapper contentMapper;
    private final ContentNewsMapper contentNewsMapper;

    /***
     * 插入新闻数据
     * @param content
     * @param contentNews
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer insertContentNews(Content content, ContentNews contentNews) {
        AppUtil.info("开始插入新闻数据 content:[{}] contentNews:[{}]", JSON.toJSONString(content), JSON.toJSONString(contentNews));
        Date now = new Date();
        content.setCreateTime(now);
        content.setUpdateTime(now);
        contentNews.setCreateTime(now);
        contentNews.setUpdateTime(now);
        ContentExample contentExample = new ContentExample();
        contentExample.createCriteria().andOutIdEqualTo(content.getOutId()).andChannelIdEqualTo(content.getChannelId());
        List<Content> contents = contentMapper.selectByExample(contentExample);
        if (contents.size() > 0) {
            AppUtil.info("对应的内容存在", JSON.toJSONString(contents));
            return contents.get(0).getContentId();
        } else {
            contentMapper.insertSelective(content);
            contentNews.setContentId(content.getContentId());
            contentNewsMapper.insertSelective(contentNews);
            AppUtil.info("插入成功 contentId", content.getContentId());
            return content.getContentId();
        }
    }
}
