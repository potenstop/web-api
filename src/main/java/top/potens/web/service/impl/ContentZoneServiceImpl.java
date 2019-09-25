package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.web.dao.db.auto.ContentZoneMapper;
import top.potens.web.model.ContentZone;
import top.potens.web.model.ContentZoneExample;
import top.potens.web.service.ContentZoneService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentZoneServiceImpl
 * @projectName web-api
 * @date 2019/9/25 17:47
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentZoneServiceImpl implements ContentZoneService {
    private final ContentZoneMapper contentZoneMapper;
    @Override
    public Map<Integer, ContentZone> selectIdList(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return new HashMap<>();
        }
        ContentZoneExample contentZoneExample = new ContentZoneExample();
        contentZoneExample.createCriteria().andContentZoneIdIn(list);
        List<ContentZone> channels = contentZoneMapper.selectByExample(contentZoneExample);
        return channels.stream().collect(Collectors.toMap(ContentZone::getContentZoneId, a -> a, (k1, k2) -> k1));
    }
}
