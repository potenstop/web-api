package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.ContentSelect;
import top.potens.web.model.ContentSelectExample;

public interface ContentSelectMapper {
    long countByExample(ContentSelectExample example);

    int deleteByExample(ContentSelectExample example);

    int deleteByPrimaryKey(Integer contentSelectId);

    int insert(ContentSelect record);

    int insertSelective(ContentSelect record);

    List<ContentSelect> selectByExample(ContentSelectExample example);

    ContentSelect selectByPrimaryKey(Integer contentSelectId);

    int updateByExampleSelective(@Param("record") ContentSelect record, @Param("example") ContentSelectExample example);

    int updateByExample(@Param("record") ContentSelect record, @Param("example") ContentSelectExample example);

    int updateByPrimaryKeySelective(ContentSelect record);

    int updateByPrimaryKey(ContentSelect record);
}