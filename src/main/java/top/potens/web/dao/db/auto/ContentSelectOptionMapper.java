package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.ContentSelectOption;
import top.potens.web.model.ContentSelectOptionExample;

public interface ContentSelectOptionMapper {
    long countByExample(ContentSelectOptionExample example);

    int deleteByExample(ContentSelectOptionExample example);

    int deleteByPrimaryKey(Integer contentSelectOptionId);

    int insert(ContentSelectOption record);

    int insertSelective(ContentSelectOption record);

    List<ContentSelectOption> selectByExample(ContentSelectOptionExample example);

    ContentSelectOption selectByPrimaryKey(Integer contentSelectOptionId);

    int updateByExampleSelective(@Param("record") ContentSelectOption record, @Param("example") ContentSelectOptionExample example);

    int updateByExample(@Param("record") ContentSelectOption record, @Param("example") ContentSelectOptionExample example);

    int updateByPrimaryKeySelective(ContentSelectOption record);

    int updateByPrimaryKey(ContentSelectOption record);
}