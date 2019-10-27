package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class CourseTypeRelation implements Serializable {
    private Integer courseTypeRelationId;

    private Date createTime;

    private Date updateTime;

    private Integer courseId;

    private Integer courseStairId;

    private Integer courseSecondId;

    private Integer courseThreeId;

    private static final long serialVersionUID = 1L;

    public Integer getCourseTypeRelationId() {
        return courseTypeRelationId;
    }

    public void setCourseTypeRelationId(Integer courseTypeRelationId) {
        this.courseTypeRelationId = courseTypeRelationId;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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