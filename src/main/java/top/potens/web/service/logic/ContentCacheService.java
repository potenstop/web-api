package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.dao.db.auto.ChannelMapper;
import top.potens.web.dao.db.auto.ContentZoneMapper;
import top.potens.web.dao.db.auto.LabelMapper;
import top.potens.web.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2019/7/21.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentCacheService {
    private final ChannelMapper channelMapper;
    private final ContentZoneMapper contentZoneMapper;
    private final LabelMapper labelMapper;

    /***
     * 根据code返回channel
     * @param channelCode code
     * @return Channel对象
     */
    @Cacheable(value = "channel", keyGenerator = "simpleKeyGenerator")
    public Channel getChannelByCode(String channelCode) {
        AppUtil.info("从mysql中根据code获取channel对象 channelCode:[{}]", channelCode);
        ChannelExample channelExample = new ChannelExample();
        channelExample.createCriteria().andChannelCodeEqualTo(channelCode);
        List<Channel> channels = channelMapper.selectByExample(channelExample);
        if (channels.isEmpty()) {
            throw new ApiException(CodeEnums.CHANNEL_CODE_NOT_FOUND.getCode(), CodeEnums.CHANNEL_CODE_NOT_FOUND.getMsg());
        }
        return channels.get(0);
    }

    /***
     * 根据code返回contentZone
     * @param zoneCode code
     * @return Channel对象
     */
    @Cacheable(value = "contentZone", keyGenerator = "simpleKeyGenerator")
    public ContentZone getContentZoneByCode(String zoneCode) {
        AppUtil.info("从mysql中根据code获取contentZone对象 contentZone:[{}]", zoneCode);
        ContentZoneExample contentZoneExample = new ContentZoneExample();
        contentZoneExample.createCriteria().andZoneCodeEqualTo(zoneCode);
        List<ContentZone> contentZones = contentZoneMapper.selectByExample(contentZoneExample);
        if (contentZones.isEmpty()) {
            throw new ApiException(CodeEnums.CONTENT_ZONE_NOT_FOUND.getCode(), CodeEnums.CONTENT_ZONE_NOT_FOUND.getMsg());
        }
        return contentZones.get(0);
    }
    /***
     * 根据labelName返回Label
     * @param labelName labelName
     * @return Channel对象
     */
    @Cacheable(value = "label", keyGenerator = "simpleKeyGenerator")
    public Label getLabelByName(String labelName) {
        AppUtil.info("从mysql中根据labelName获取label对象 labelName:[{}]", labelName);
        LabelExample labelExample = new LabelExample();
        labelExample.createCriteria().andLabelNameEqualTo(labelName);
        List<Label> labels = labelMapper.selectByExample(labelExample);
        if (labels.isEmpty()) {
            Label label = new Label();
            Date now = new Date();
            label.setLabelName(labelName);
            label.setCreateTime(now);
            label.setUpdateTime(now);
            labelMapper.insertSelective(label);
            return label;
        }
        return labels.get(0);
    }
}
