package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.AlbumCourseProblemTopic;
import top.potens.web.model.AlbumCourseProblemTopicExample;

public interface AlbumCourseProblemTopicMapper {
    long countByExample(AlbumCourseProblemTopicExample example);

    int deleteByExample(AlbumCourseProblemTopicExample example);

    int deleteByPrimaryKey(Integer albumCourseProblemTopicId);

    int insert(AlbumCourseProblemTopic record);

    int insertSelective(AlbumCourseProblemTopic record);

    List<AlbumCourseProblemTopic> selectByExample(AlbumCourseProblemTopicExample example);

    AlbumCourseProblemTopic selectByPrimaryKey(Integer albumCourseProblemTopicId);

    int updateByExampleSelective(@Param("record") AlbumCourseProblemTopic record, @Param("example") AlbumCourseProblemTopicExample example);

    int updateByExample(@Param("record") AlbumCourseProblemTopic record, @Param("example") AlbumCourseProblemTopicExample example);

    int updateByPrimaryKeySelective(AlbumCourseProblemTopic record);

    int updateByPrimaryKey(AlbumCourseProblemTopic record);
}