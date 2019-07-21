package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.dao.db.auto.ChannelMapper;
import top.potens.web.model.Channel;
import top.potens.web.model.ChannelExample;
import top.potens.web.service.ChannelService;

import java.util.List;

/**
 * Created by wenshao on 2019/7/20.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelServiceImpl implements ChannelService {

}
