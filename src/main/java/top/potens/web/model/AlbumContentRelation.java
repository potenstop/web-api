package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class AlbumContentRelation implements Serializable {
    private Integer albumContentRelationId;

    private Date createTime;

    private Date updateTime;

    private Integer albumId;

    private Integer contentId;

    private static final long serialVersionUID = 1L;

    public Integer getAlbumContentRelationId() {
        return albumContentRelationId;
    }

    public void setAlbumContentRelationId(Integer albumContentRelationId) {
        this.albumContentRelationId = albumContentRelationId;
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

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}