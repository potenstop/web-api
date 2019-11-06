package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.ContentTopicSelectOption;
import top.potens.web.model.ContentTopicSelectOptionExample;

public interface ContentTopicSelectOptionMapper {
    long countByExample(ContentTopicSelectOptionExample example);

    int deleteByExample(ContentTopicSelectOptionExample example);

    int deleteByPrimaryKey(Integer contentTopicSelectOptionId);

    int insert(ContentTopicSelectOption record);

    int insertSelective(ContentTopicSelectOption record);

    List<ContentTopicSelectOption> selectByExample(ContentTopicSelectOptionExample example);

    ContentTopicSelectOption selectByPrimaryKey(Integer contentTopicSelectOptionId);

    int updateByExampleSelective(@Param("record") ContentTopicSelectOption record, @Param("example") ContentTopicSelectOptionExample example);

    int updateByExample(@Param("record") ContentTopicSelectOption record, @Param("example") ContentTopicSelectOptionExample example);

    int updateByPrimaryKeySelective(ContentTopicSelectOption record);

    int updateByPrimaryKey(ContentTopicSelectOption record);
}