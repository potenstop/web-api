package top.potens.web.dao.db.tool.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.MemberAuth;
import top.potens.web.model.MemberAuthExample;

public interface MemberAuthMapper {
    long countByExample(MemberAuthExample example);

    int deleteByExample(MemberAuthExample example);

    int insert(MemberAuth record);

    int insertSelective(MemberAuth record);

    List<MemberAuth> selectByExample(MemberAuthExample example);

    int updateByExampleSelective(@Param("record") MemberAuth record, @Param("example") MemberAuthExample example);

    int updateByExample(@Param("record") MemberAuth record, @Param("example") MemberAuthExample example);
}