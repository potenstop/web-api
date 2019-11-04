package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.potens.web.dao.db.auto.CourseMapper;
import top.potens.web.dao.db.auto.CourseTypeRelationMapper;
import top.potens.web.model.Course;
import top.potens.web.model.CourseTypeExample;
import top.potens.web.model.CourseTypeRelation;
import top.potens.web.model.CourseTypeRelationExample;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseServiceLogic
 * @projectName web-api
 * @date 2019/11/1 14:23
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseServiceLogic {
    private final CourseMapper courseMapper;
    private final CourseTypeRelationMapper courseTypeRelationMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertDataAndRelation(Course course, List<CourseTypeRelation> courseTypeRelationList) {
        Date now = new Date();
        course.setCreateTime(now);
        course.setUpdateTime(now);
        courseMapper.insertSelective(course);
        courseTypeRelationList.forEach(courseTypeRelation -> {
            courseTypeRelation.setCourseId(course.getCourseId());
            courseTypeRelation.setCreateTime(now);
            courseTypeRelation.setUpdateTime(now);
            courseTypeRelationMapper.insertSelective(courseTypeRelation);
        });
    }
    @Transactional(rollbackFor = Exception.class)
    public void deleteRelationAndUpdate(Course course, List<CourseTypeRelation> courseTypeRelationList) {
        Date now = new Date();
        course.setUpdateTime(now);
        courseMapper.updateByPrimaryKeySelective(course);
        CourseTypeRelationExample courseTypeRelationExample = new CourseTypeRelationExample();
        courseTypeRelationExample.createCriteria().andCourseIdEqualTo(course.getCourseId());
        courseTypeRelationMapper.deleteByExample(courseTypeRelationExample);
        courseTypeRelationList.forEach(courseTypeRelation -> {
            courseTypeRelation.setCourseId(course.getCourseId());
            courseTypeRelation.setCreateTime(now);
            courseTypeRelation.setUpdateTime(now);
            courseTypeRelationMapper.insertSelective(courseTypeRelation);
        });
    }
}
