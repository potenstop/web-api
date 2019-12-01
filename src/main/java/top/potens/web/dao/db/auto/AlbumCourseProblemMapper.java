package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.AlbumCourseProblem;
import top.potens.web.model.AlbumCourseProblemExample;

public interface AlbumCourseProblemMapper {
    long countByExample(AlbumCourseProblemExample example);

    int deleteByExample(AlbumCourseProblemExample example);

    int deleteByPrimaryKey(Integer albumCourseProblemId);

    int insert(AlbumCourseProblem record);

    int insertSelective(AlbumCourseProblem record);

    List<AlbumCourseProblem> selectByExample(AlbumCourseProblemExample example);

    AlbumCourseProblem selectByPrimaryKey(Integer albumCourseProblemId);

    int updateByExampleSelective(@Param("record") AlbumCourseProblem record, @Param("example") AlbumCourseProblemExample example);

    int updateByExample(@Param("record") AlbumCourseProblem record, @Param("example") AlbumCourseProblemExample example);

    int updateByPrimaryKeySelective(AlbumCourseProblem record);

    int updateByPrimaryKey(AlbumCourseProblem record);
}