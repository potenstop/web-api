package top.potens.web.service;

import top.potens.framework.model.PageResponse;
import top.potens.web.model.ContentTopic;
import top.potens.web.request.ContentTopicAddRequest;
import top.potens.web.request.ContentTopicListItemRequest;
import top.potens.web.request.ContentTopicMulAddRequest;
import top.potens.web.request.ContentTopicUpdateRequest;
import top.potens.web.response.ContentTopicListItemResponse;
import top.potens.web.response.ContentTopicViewResponse;

import java.util.List;
import java.util.Map;


/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseService
 * @projectName web-api
 * @date 2019/10/22 10:02
 */
public interface ContentTopicService {
    /**
     *
     * 方法功能描述: 按单个内容id获取单个对象
     *
     * @author yanshaowen
     * @date 2019/11/6 14:52
     * @param contentId
     * @return
     * @throws
     */
    ContentTopic byId(Integer contentId);

    /**
     *
     * 方法功能描述: 按多个内容id获取多个对象
     *
     * @author yanshaowen
     * @date 2019/11/6 14:53
     * @param contentIdList
     * @return
     * @throws
     */
    List<ContentTopic> byIdList(List<Integer> contentIdList);

    /**
     *
     * 方法功能描述: 按多个内容id获取多个对象
     *
     * @author yanshaowen
     * @date 2019/11/6 14:53
     * @param contentIdList
     * @return
     * @throws
     */
    Map<Integer, ContentTopic> byIdListResultMap(List<Integer> contentIdList);
    /**
    *
    * 方法功能描述: 获取课程的题目列表
    *
    * @author yanshaowen
    * @date 2019/10/22 10:35
    * @param request    request
    * @return
    * @throws
    */
    PageResponse<ContentTopicListItemResponse> selectList(ContentTopicListItemRequest request);

    /**
     *
     * 方法功能描述: 插入一个课程题目
     *
     * @author yanshaowen
     * @date 2019/10/22 10:35
     * @param request    request
     * @return
     * @throws
     */
    Integer insertOne(ContentTopicAddRequest request);

    /**
    *
    * 方法功能描述: 按内容id 查询课程题目对象
    *
    * @author yanshaowen
    * @date 2019/11/5 11:56
    * @param contentId
    * @return
    * @throws
    */
    ContentTopicViewResponse selectById(Integer contentId);

    /**
     *
     * 方法功能描述: 按多个内容id 查询课程题目对象
     *
     * @author yanshaowen
     * @date 2019/11/5 11:56
     * @param contentIdList
     * @return
     * @throws
     */
    List<ContentTopicViewResponse> selectByIdList(List<Integer> contentIdList);

    /**
    *
    * 方法功能描述: 按id更新课程题目
    *
    * @author yanshaowen
    * @date 2019/11/5 14:35
    * @param request
    * @return
    * @throws
    */
    Integer updateById(ContentTopicUpdateRequest request);

    /**
    *
    * 方法功能描述: 插入多条
    *
    * @author yanshaowen
    * @date 2019/11/29 14:25
    * @param request
    * @return
    * @throws
    */
    Integer insertMultiple(ContentTopicMulAddRequest request);
}
