package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.web.code.CommonCode;
import top.potens.web.code.ContentCode;
import top.potens.web.dao.db.auto.*;
import top.potens.web.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2019/7/21.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheServiceLogic {
    private final ChannelMapper channelMapper;
    private final ContentZoneMapper contentZoneMapper;
    private final LabelMapper labelMapper;
    private final CourseTypeMapper courseTypeMapper;
    private final CourseMapper courseMapper;

    /***
     * 根据code返回channel
     * @param channelCode code
     * @return Channel对象
     */
    @Cacheable(value = "channel", keyGenerator = "simpleKeyGenerator")
    public Channel getChannelByCode(String channelCode) {
        AppLogger.info("从mysql中根据code获取channel对象 channelCode:[{}]", channelCode);
        ChannelExample channelExample = new ChannelExample();
        channelExample.createCriteria().andChannelCodeEqualTo(channelCode);
        List<Channel> channels = channelMapper.selectByExample(channelExample);
        if (channels.isEmpty()) {
            throw new ApiException(CommonCode.CHANNEL_CODE_NOT_FOUND);
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
        AppLogger.info("从mysql中根据code获取contentZone对象 contentZone:[{}]", zoneCode);
        ContentZoneExample contentZoneExample = new ContentZoneExample();
        contentZoneExample.createCriteria().andZoneCodeEqualTo(zoneCode);
        List<ContentZone> contentZones = contentZoneMapper.selectByExample(contentZoneExample);
        if (contentZones.isEmpty()) {
            throw new ApiException(ContentCode.CONTENT_ZONE_NOT_FOUND);
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
        AppLogger.info("从mysql中根据labelName获取label对象 labelName:[{}]", labelName);
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

    @Cacheable(value = "courseType", keyGenerator = "simpleKeyGenerator")
    public List<CourseType> getCourseTypeAll() {
        CourseTypeExample courseTypeExample = new CourseTypeExample();
        courseTypeExample.setOrderByClause("rank asc, sequence asc");
        return courseTypeMapper.selectByExample(courseTypeExample);
    }

    @Cacheable(value = "course", keyGenerator = "simpleKeyGenerator")
    public List<Course> getCourseAll() {
        CourseExample courseExample = new CourseExample();
        return courseMapper.selectByExample(courseExample);
    }

}
