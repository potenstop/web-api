package top.potens.web.service;

import top.potens.framework.model.PageResponse;
import top.potens.web.model.Course;
import top.potens.web.request.CourseAddRequest;
import top.potens.web.request.CourseListItemRequest;
import top.potens.web.response.CourseListItemResponse;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseService
 * @projectName web-api
 * @date 2019/10/28 6:11
 */
public interface CourseService {
    /**
     *
     * 方法功能描述: 根据id批量查询课程对象
     *
     * @author yanshaowen
     * @date 2019/9/25 17:46
     * @param idList
     * @return
     * @throws
     */
    Map<Integer, Course> selectNameByIdList(List<Integer> idList);

    /**
     *
     * 方法功能描述: 根据id批量查询课程对象
     *
     * @author yanshaowen
     * @date 2019/9/25 17:46
     * @param courseId
     * @param courseName
     * @param courseStairId
     * @param courseSecondId
     * @param courseThreeId
     * @return
     * @throws
     */
    List<CourseListItemResponse> selectListByFilterNotPage(Integer courseId,
                                                           String courseName,
                                                           String courseCode,
                                                           Integer courseStairId,
                                                           Integer courseSecondId,
                                                           Integer courseThreeId);

    /**
    *
    * 方法功能描述: 插入一条记录
    *
    * @author yanshaowen
    * @date 2019/11/1 11:08
    * @param request
    * @return
    * @throws
    */
    Integer insertOne(CourseAddRequest request);

    /**
     *
     * 方法功能描述: 分页查询列表
     *
     * @author yanshaowen
     * @date 2019/11/1 11:08
     * @param request
     * @return
     * @throws
     */
    PageResponse<CourseListItemResponse> selectList(CourseListItemRequest request);

}
