package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.web.common.constant.AreaConstant;
import top.potens.web.dao.db.auto.AreaMapper;
import top.potens.web.model.Area;
import top.potens.web.model.AreaExample;
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
    private final AreaMapper areaMapper;

    @Override
    public List<Area> selectCities() {
        AreaExample areaExample = new AreaExample();
        areaExample.createCriteria().andRankEqualTo(AreaConstant.Rank.CITY);
        return areaMapper.selectByExample(areaExample);
    }
}
