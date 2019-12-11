package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class AlbumCourseProblem implements Serializable {
    private Integer albumCourseProblemId;

    private Date createTime;

    private Date updateTime;

    private Integer albumId;

    private Integer albumCourseId;

    private Integer userId;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getAlbumCourseProblemId() {
        return albumCourseProblemId;
    }

    public void setAlbumCourseProblemId(Integer albumCourseProblemId) {
        this.albumCourseProblemId = albumCourseProblemId;
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

    public Integer getAlbumCourseId() {
        return albumCourseId;
    }

    public void setAlbumCourseId(Integer albumCourseId) {
        this.albumCourseId = albumCourseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}