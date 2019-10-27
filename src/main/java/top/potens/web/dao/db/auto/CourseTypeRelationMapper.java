package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.CourseTypeRelation;
import top.potens.web.model.CourseTypeRelationExample;

public interface CourseTypeRelationMapper {
    long countByExample(CourseTypeRelationExample example);

    int deleteByExample(CourseTypeRelationExample example);

    int deleteByPrimaryKey(Integer courseTypeRelationId);

    int insert(CourseTypeRelation record);

    int insertSelective(CourseTypeRelation record);

    List<CourseTypeRelation> selectByExample(CourseTypeRelationExample example);

    CourseTypeRelation selectByPrimaryKey(Integer courseTypeRelationId);

    int updateByExampleSelective(@Param("record") CourseTypeRelation record, @Param("example") CourseTypeRelationExample example);

    int updateByExample(@Param("record") CourseTypeRelation record, @Param("example") CourseTypeRelationExample example);

    int updateByPrimaryKeySelective(CourseTypeRelation record);

    int updateByPrimaryKey(CourseTypeRelation record);
}