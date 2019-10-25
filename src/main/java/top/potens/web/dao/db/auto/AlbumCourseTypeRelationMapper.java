package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.AlbumCourseTypeRelation;
import top.potens.web.model.AlbumCourseTypeRelationExample;

public interface AlbumCourseTypeRelationMapper {
    long countByExample(AlbumCourseTypeRelationExample example);

    int deleteByExample(AlbumCourseTypeRelationExample example);

    int deleteByPrimaryKey(Integer albumCourseTypeRelationId);

    int insert(AlbumCourseTypeRelation record);

    int insertSelective(AlbumCourseTypeRelation record);

    List<AlbumCourseTypeRelation> selectByExample(AlbumCourseTypeRelationExample example);

    AlbumCourseTypeRelation selectByPrimaryKey(Integer albumCourseTypeRelationId);

    int updateByExampleSelective(@Param("record") AlbumCourseTypeRelation record, @Param("example") AlbumCourseTypeRelationExample example);

    int updateByExample(@Param("record") AlbumCourseTypeRelation record, @Param("example") AlbumCourseTypeRelationExample example);

    int updateByPrimaryKeySelective(AlbumCourseTypeRelation record);

    int updateByPrimaryKey(AlbumCourseTypeRelation record);
}