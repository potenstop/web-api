package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.ContentLabel;
import top.potens.web.model.ContentLabelExample;

public interface ContentLabelMapper {
    long countByExample(ContentLabelExample example);

    int deleteByExample(ContentLabelExample example);

    int insert(ContentLabel record);

    int insertSelective(ContentLabel record);

    List<ContentLabel> selectByExample(ContentLabelExample example);

    int updateByExampleSelective(@Param("record") ContentLabel record, @Param("example") ContentLabelExample example);

    int updateByExample(@Param("record") ContentLabel record, @Param("example") ContentLabelExample example);
}