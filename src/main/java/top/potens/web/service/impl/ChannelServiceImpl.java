package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.web.dao.db.auto.ChannelMapper;
import top.potens.web.model.Channel;
import top.potens.web.model.ChannelExample;
import top.potens.web.model.ContentNews;
import top.potens.web.service.ChannelService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ChannelServiceImpl
 * @projectName web-api
 * @date 2019/9/25 12:31
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelServiceImpl extends AbstractSimpleTableCommonServiceImpl<Channel> implements ChannelService{
    private final ChannelMapper channelMapper;

    @Override
    protected Channel mapperByPrimaryKey(Integer id) {
        return channelMapper.selectByPrimaryKey(id);
    }

    @Override
    protected Channel mapperBySecondPrimaryKey(Integer id) {
        return null;
    }

    @Override
    protected Boolean isDelete(Channel channel) {
        return false;
    }

    @Override
    public Map<Integer, Channel> selectIdList(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return new HashMap<>();
        }
        ChannelExample channelExample = new ChannelExample();
        channelExample.createCriteria().andChannelIdIn(list);
        List<Channel> channels = channelMapper.selectByExample(channelExample);
        return channels.stream().collect(Collectors.toMap(Channel::getChannelId, a -> a, (k1, k2) -> k1));
    }
}
