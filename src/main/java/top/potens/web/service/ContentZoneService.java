package top.potens.web.service;

import top.potens.web.model.ContentZone;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentZoneService
 * @projectName web-api
 * @date 2019/9/25 17:45
 */
public interface ContentZoneService {
    /**
    *
    * 方法功能描述: 根据id批量查询频道对象
    *
    * @author yanshaowen
    * @date 2019/9/25 17:46
    * @param list
    * @return
    * @throws
    */
    Map<Integer, ContentZone> selectIdList(List<Integer> list);
}
