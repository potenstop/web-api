package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.UserAuth;
import top.potens.web.model.UserAuthExample;

public interface UserAuthMapper {
    long countByExample(UserAuthExample example);

    int deleteByExample(UserAuthExample example);

    int deleteByPrimaryKey(Integer userAuthId);

    int insert(UserAuth record);

    int insertSelective(UserAuth record);

    List<UserAuth> selectByExample(UserAuthExample example);

    UserAuth selectByPrimaryKey(Integer userAuthId);

    int updateByExampleSelective(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    int updateByExample(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    int updateByPrimaryKeySelective(UserAuth record);

    int updateByPrimaryKey(UserAuth record);
}