package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.ContentNews;
import top.potens.web.model.ContentNewsExample;

public interface ContentNewsMapper {
    long countByExample(ContentNewsExample example);

    int deleteByExample(ContentNewsExample example);

    int deleteByPrimaryKey(Integer contentNewsId);

    int insert(ContentNews record);

    int insertSelective(ContentNews record);

    List<ContentNews> selectByExampleWithBLOBs(ContentNewsExample example);

    List<ContentNews> selectByExample(ContentNewsExample example);

    ContentNews selectByPrimaryKey(Integer contentNewsId);

    int updateByExampleSelective(@Param("record") ContentNews record, @Param("example") ContentNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") ContentNews record, @Param("example") ContentNewsExample example);

    int updateByExample(@Param("record") ContentNews record, @Param("example") ContentNewsExample example);

    int updateByPrimaryKeySelective(ContentNews record);

    int updateByPrimaryKeyWithBLOBs(ContentNews record);

    int updateByPrimaryKey(ContentNews record);
}