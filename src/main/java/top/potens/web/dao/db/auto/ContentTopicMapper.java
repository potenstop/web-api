package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.ContentTopic;
import top.potens.web.model.ContentTopicExample;

public interface ContentTopicMapper {
    long countByExample(ContentTopicExample example);

    int deleteByExample(ContentTopicExample example);

    int deleteByPrimaryKey(Integer contentTopicId);

    int insert(ContentTopic record);

    int insertSelective(ContentTopic record);

    List<ContentTopic> selectByExample(ContentTopicExample example);

    ContentTopic selectByPrimaryKey(Integer contentTopicId);

    int updateByExampleSelective(@Param("record") ContentTopic record, @Param("example") ContentTopicExample example);

    int updateByExample(@Param("record") ContentTopic record, @Param("example") ContentTopicExample example);

    int updateByPrimaryKeySelective(ContentTopic record);

    int updateByPrimaryKey(ContentTopic record);
}