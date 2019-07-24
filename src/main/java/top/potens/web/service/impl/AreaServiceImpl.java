package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.web.bmo.AreaCodeNameBo;
import top.potens.web.common.constant.AreaConstant;
import top.potens.web.dao.db.ext.AreaExMapper;
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
public class AreaServiceImpl implements AreaService {
    private final AreaExMapper areaExMapper;

    @Override
    public List<AreaCodeNameBo> selectCities() {
        List<AreaCodeNameBo> areaCodeNameBos = areaExMapper.selectCityList();
        areaCodeNameBos.forEach(area ->{
            area.setRank(AreaConstant.Rank.CITY);
        });
        return  areaCodeNameBos;
    }
}
