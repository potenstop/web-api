package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class AlbumCourse implements Serializable {
    private Integer albumCourseId;

    private Date createTime;

    private Date updateTime;

    private Integer albumId;

    private Integer courseId;

    private static final long serialVersionUID = 1L;

    public Integer getAlbumCourseId() {
        return albumCourseId;
    }

    public void setAlbumCourseId(Integer albumCourseId) {
        this.albumCourseId = albumCourseId;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}