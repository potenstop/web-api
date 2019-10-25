package top.potens.web.dao.db.ext;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseExMapper
 * @projectName web-api
 * @date 2019/10/22 14:56
 */
public interface AlbumCourseExMapper {
    /**
    *
    * 方法功能描述: 按条件查询页数
    *
    * @author yanshaowen
    * @date 2019/10/22 15:31
    * @param albumId
    * @param albumName
    * @param createTimeStart
    * @param createTimeEnd
    * @return
    * @throws
    */
    Long selectAlbumCount(
            @Param("albumId") Integer albumId, @Param("albumName") String albumName,
            @Param("createTimeStart") Date createTimeStart, @Param("createTimeEnd") Date createTimeEnd
    );
    /**
    *
    * 方法功能描述: 按条件查询每页的id列表
    *
    * @author yanshaowen
    * @date 2019/10/22 15:30
    * @param limit
    * @param offset
    * @param albumId
    * @param albumName
    * @param createTimeStart
    * @param createTimeEnd
    * @return
    * @throws
    */

    List<Integer> selectAlbumIdList(
            @Param("limit") Integer limit, @Param("offset") Long offset, @Param("orderBy") String orderBy,
            @Param("albumId") Integer albumId, @Param("albumName") String albumName,
            @Param("createTimeStart") Date createTimeStart, @Param("createTimeEnd") Date createTimeEnd);
}
