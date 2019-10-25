package top.potens.web.dao.db.ext;

import top.potens.web.bmo.AreaCodeNameBo;
import top.potens.web.model.ContentLabel;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AreaExMapper
 * @projectName web-api
 * @date 2019/10/22 14:56
 */
public interface AreaExMapper {
    public List<AreaCodeNameBo> selectCityList();
}
