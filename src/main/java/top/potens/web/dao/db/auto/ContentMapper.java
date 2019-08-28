package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.Content;
import top.potens.web.model.ContentExample;

public interface ContentMapper {
    long countByExample(ContentExample example);

    int deleteByExample(ContentExample example);

    int deleteByPrimaryKey(Integer contentId);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExample(ContentExample example);

    Content selectByPrimaryKey(Integer contentId);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}