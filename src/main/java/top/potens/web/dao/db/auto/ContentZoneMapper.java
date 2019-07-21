package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.ContentZone;
import top.potens.web.model.ContentZoneExample;

public interface ContentZoneMapper {
    long countByExample(ContentZoneExample example);

    int deleteByExample(ContentZoneExample example);

    int insert(ContentZone record);

    int insertSelective(ContentZone record);

    List<ContentZone> selectByExample(ContentZoneExample example);

    int updateByExampleSelective(@Param("record") ContentZone record, @Param("example") ContentZoneExample example);

    int updateByExample(@Param("record") ContentZone record, @Param("example") ContentZoneExample example);
}