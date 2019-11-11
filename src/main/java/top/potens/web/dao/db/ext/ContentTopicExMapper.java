package top.potens.web.dao.db.ext;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicExMapper
 * @projectName web-api
 * @date 2019/10/22 14:56
 */
public interface ContentTopicExMapper {
    /**
    *
    * 方法功能描述: 按条件过滤 返回id列表
    *
    * @author yanshaowen
    * @date 2019/11/6 14:37
    * @param contentId
    * @param createTimeStart
    * @param createTimeEnd
    * @param title
    * @param state
    * @param orderBy
    * @param contentIdList
    * @return
    * @throws
    */
    List<Integer> selectIdListByFilter(
            @Param("contentId") Integer contentId,
            @Param("createTimeStart") Date createTimeStart,
            @Param("createTimeEnd") Date createTimeEnd,
            @Param("title") String title,
            @Param("state") Integer state,
            @Param("myOrderBy") String orderBy,
            @Param("contentIdList") List<Integer> contentIdList
    );
}
