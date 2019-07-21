package top.potens.web.request;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * Created by wenshao on 2019/7/21.
 */
@ApiModel("外部新闻类")
public class ContentNewsOutRequest {
    private String id;

    private String url;

    private String html;

    private String title;

    private String articleSource;

    private String webSource;

    private String editor;

    private String time;

    private String zone;

    private List<String> labels;

    private List<ContentCommentOutRequest> commentList;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml() {
        return this.html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleSource() {
        return this.articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
    }

    public String getWebSource() {
        return this.webSource;
    }

    public void setWebSource(String webSource) {
        this.webSource = webSource;
    }

    public String getEditor() {
        return this.editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZone() {
        return this.zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public List<String> getLabels() {
        return this.labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<ContentCommentOutRequest> getCommentList() {
        return this.commentList;
    }

    public void setCommentList(List<ContentCommentOutRequest> commentList) {
        this.commentList = commentList;
    }
}


