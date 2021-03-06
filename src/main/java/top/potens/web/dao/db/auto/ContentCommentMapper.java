package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.ContentComment;
import top.potens.web.model.ContentCommentExample;

public interface ContentCommentMapper {
    long countByExample(ContentCommentExample example);

    int deleteByExample(ContentCommentExample example);

    int deleteByPrimaryKey(Integer contentCommentId);

    int insert(ContentComment record);

    int insertSelective(ContentComment record);

    List<ContentComment> selectByExample(ContentCommentExample example);

    ContentComment selectByPrimaryKey(Integer contentCommentId);

    int updateByExampleSelective(@Param("record") ContentComment record, @Param("example") ContentCommentExample example);

    int updateByExample(@Param("record") ContentComment record, @Param("example") ContentCommentExample example);

    int updateByPrimaryKeySelective(ContentComment record);

    int updateByPrimaryKey(ContentComment record);
}