package top.potens.web.dao.db.ext;

import top.potens.web.bmo.AreaCodeNameBo;
import top.potens.web.model.ContentLabel;

import java.util.List;

/**
 * Created by wenshao on 2019/7/21.
 */
public interface AreaExMapper {
    public List<AreaCodeNameBo> selectCityList();
}
