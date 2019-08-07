package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class ContentNews implements Serializable {
    private Integer contentNewsId;

    private Date createTime;

    private Date updateTime;

    private Integer contentId;

    private String articleSource;

    private String title;

    private String editor;

    private Date publishTime;

    private Integer contentZoneId;

    private String token;

    private String article;

    private static final long serialVersionUID = 1L;

    public Integer getContentNewsId() {
        return contentNewsId;
    }

    public void setContentNewsId(Integer contentNewsId) {
        this.contentNewsId = contentNewsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource == null ? null : articleSource.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getContentZoneId() {
        return contentZoneId;
    }

    public void setContentZoneId(Integer contentZoneId) {
        this.contentZoneId = contentZoneId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article == null ? null : article.trim();
    }
}