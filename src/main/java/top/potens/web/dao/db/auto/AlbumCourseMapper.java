package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.AlbumCourse;
import top.potens.web.model.AlbumCourseExample;

public interface AlbumCourseMapper {
    long countByExample(AlbumCourseExample example);

    int deleteByExample(AlbumCourseExample example);

    int deleteByPrimaryKey(Integer albumCourseId);

    int insert(AlbumCourse record);

    int insertSelective(AlbumCourse record);

    List<AlbumCourse> selectByExample(AlbumCourseExample example);

    AlbumCourse selectByPrimaryKey(Integer albumCourseId);

    int updateByExampleSelective(@Param("record") AlbumCourse record, @Param("example") AlbumCourseExample example);

    int updateByExample(@Param("record") AlbumCourse record, @Param("example") AlbumCourseExample example);

    int updateByPrimaryKeySelective(AlbumCourse record);

    int updateByPrimaryKey(AlbumCourse record);
}