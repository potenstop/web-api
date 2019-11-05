package top.potens.web.service;

import top.potens.framework.model.PageResponse;
import top.potens.web.request.AlbumCourseAddRequest;
import top.potens.web.request.AlbumCourseListItemRequest;
import top.potens.web.request.AlbumCourseUpdateRequest;
import top.potens.web.response.AlbumCourseListItemResponse;
import top.potens.web.response.AlbumCourseViewResponse;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseService
 * @projectName web-api
 * @date 2019/10/22 10:02
 */
public interface AlbumCourseService {
    /**
    *
    * 方法功能描述: 获取课程的专辑列表
    *
    * @author yanshaowen
    * @date 2019/10/22 10:35
    * @param request    request
    * @return
    * @throws
    */
    PageResponse<AlbumCourseListItemResponse> selectList(AlbumCourseListItemRequest request);

    /**
     *
     * 方法功能描述: 插入一个课程专辑
     *
     * @author yanshaowen
     * @date 2019/10/22 10:35
     * @param request    request
     * @return
     * @throws
     */
    Integer insertOne(AlbumCourseAddRequest request);

    /**
    *
    * 方法功能描述: 按专辑id 查询课程专辑对象
    *
    * @author yanshaowen
    * @date 2019/11/5 11:56
    * @param albumId
    * @return
    * @throws
    */
    AlbumCourseViewResponse viewById(Integer albumId);

    /**
    *
    * 方法功能描述: 按id更新课程专辑
    *
    * @author yanshaowen
    * @date 2019/11/5 14:35
    * @param request
    * @return
    * @throws
    */
    Integer updateById(AlbumCourseUpdateRequest request);
}
