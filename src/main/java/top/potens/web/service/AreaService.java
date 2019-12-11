package top.potens.web.service;

import top.potens.framework.service.TableCommonService;
import top.potens.web.bmo.AreaCodeNameBo;
import top.potens.web.model.Area;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AreaService
 * @projectName web-api
 * @date 2019/7/5 13:43
 */
public interface AreaService extends TableCommonService<Area> {
    /**
    *
    * 方法功能描述: 获取所有的城市地区列表
    *
    * @author yanshaowen
    * @date 2019/10/22 10:03
    * @return
    * @throws
    */
    List<AreaCodeNameBo> selectCities();
}
