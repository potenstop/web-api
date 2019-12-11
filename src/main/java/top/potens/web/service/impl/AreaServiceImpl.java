package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.web.bmo.AreaCodeNameBo;
import top.potens.web.common.constant.AreaConstant;
import top.potens.web.dao.db.auto.AreaMapper;
import top.potens.web.dao.db.ext.AreaExMapper;
import top.potens.web.model.Area;
import top.potens.web.service.AreaService;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AreaServiceImpl
 * @projectName web-api
 * @date 2019/7/5 13:44
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AreaServiceImpl extends AbstractSimpleTableCommonServiceImpl<Area> implements AreaService {
    private final AreaExMapper areaExMapper;
    private final AreaMapper areaMapper;

    @Override
    protected Area mapperByPrimaryKey(Integer id) {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    protected Area mapperBySecondPrimaryKey(Integer id) {
        return null;
    }

    @Override
    protected Boolean isDelete(Area area) {
        return false;
    }
    @Override
    public List<AreaCodeNameBo> selectCities() {
        List<AreaCodeNameBo> areaCodeNameBos = areaExMapper.selectCityList();
        areaCodeNameBos.forEach(area ->{
            area.setRank(AreaConstant.Rank.CITY);
        });
        return  areaCodeNameBos;
    }
}
