package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.Channel;
import top.potens.web.model.ChannelExample;

public interface ChannelMapper {
    long countByExample(ChannelExample example);

    int deleteByExample(ChannelExample example);

    int deleteByPrimaryKey(Integer channelId);

    int insert(Channel record);

    int insertSelective(Channel record);

    List<Channel> selectByExample(ChannelExample example);

    Channel selectByPrimaryKey(Integer channelId);

    int updateByExampleSelective(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByExample(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}