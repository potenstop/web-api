package top.potens.web.service;

import top.potens.framework.service.TableCommonService;
import top.potens.web.model.Channel;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ChannelService
 * @projectName web-api
 * @date 2019/9/25 12:31
 */
public interface ChannelService extends TableCommonService<Channel> {
    /**
    *
    * 方法功能描述: 根据id批量查询渠道对象
    *
    * @author yanshaowen
    * @date 2019/9/25 15:24
    * @param list
    * @return
    * @throws
    */
    Map<Integer, Channel> selectIdList(List<Integer> list);

}
