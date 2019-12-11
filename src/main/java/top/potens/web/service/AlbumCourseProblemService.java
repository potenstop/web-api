package top.potens.web.service;

import top.potens.framework.model.PageResponse;
import top.potens.framework.service.TableCommonService;
import top.potens.web.model.AlbumCourseProblem;
import top.potens.web.request.AlbumCourseProblemAddRequest;
import top.potens.web.request.AlbumCourseProblemListItemRequest;
import top.potens.web.response.AlbumCourseProblemListItemResponse;
import top.potens.web.response.AlbumCourseProblemTopicResponse;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentCourseService
 * @projectName web-api
 * @date 2019/10/25 14:22
 */
public interface AlbumCourseProblemService extends TableCommonService<AlbumCourseProblem> {
    /**
     *
     * 方法功能描述: 插入一个答题记录
     *
     * @author yanshaowen
     * @date 2019/10/22 10:35
     * @param request    request
     * @param userId     userId
     * @return
     * @throws
     */
    Integer insertOne(AlbumCourseProblemAddRequest request, Integer userId);

    /**
    *
    * 方法功能描述:
    *
    * @author yanshaowen
    * @date 2019/12/11 14:29
    * @param request
    * @return
    * @throws
    */
    PageResponse<AlbumCourseProblemListItemResponse> selectList(AlbumCourseProblemListItemRequest request);

    /**
    *
    * 方法功能描述: 查询试卷下的已答题目
    *
    * @author yanshaowen
    * @date 2019/12/11 15:02
    * @param albumCourseProblemTopicId
    * @return
    * @throws
    */
    List<AlbumCourseProblemTopicResponse> selectTopicList(Integer albumCourseProblemTopicId);
}
