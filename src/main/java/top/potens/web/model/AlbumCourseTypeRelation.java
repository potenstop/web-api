package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class AlbumCourseTypeRelation implements Serializable {
    private Integer albumCourseTypeRelationId;

    private Date createTime;

    private Date updateTime;

    private Integer albumId;

    private Integer courseTypeId;

    private Integer courseStairId;

    private Integer courseSecondId;

    private Integer courseThreeId;

    private static final long serialVersionUID = 1L;

    public Integer getAlbumCourseTypeRelationId() {
        return albumCourseTypeRelationId;
    }

    public void setAlbumCourseTypeRelationId(Integer albumCourseTypeRelationId) {
        this.albumCourseTypeRelationId = albumCourseTypeRelationId;
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

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Integer getCourseStairId() {
        return courseStairId;
    }

    public void setCourseStairId(Integer courseStairId) {
        this.courseStairId = courseStairId;
    }

    public Integer getCourseSecondId() {
        return courseSecondId;
    }

    public void setCourseSecondId(Integer courseSecondId) {
        this.courseSecondId = courseSecondId;
    }

    public Integer getCourseThreeId() {
        return courseThreeId;
    }

    public void setCourseThreeId(Integer courseThreeId) {
        this.courseThreeId = courseThreeId;
    }
}