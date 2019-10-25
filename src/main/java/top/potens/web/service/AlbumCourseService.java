package top.potens.web.service;

import top.potens.framework.model.PageResponse;
import top.potens.web.request.AlbumCourseListItemRequest;
import top.potens.web.response.AlbumCourseListItemResponse;

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
    PageResponse<AlbumCourseListItemResponse> selectCourseList(AlbumCourseListItemRequest request);
}
