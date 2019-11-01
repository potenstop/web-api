package top.potens.web.service;

import top.potens.web.model.CourseType;
import top.potens.web.response.CourseTypeListItemResponse;
import top.potens.web.response.CourseTypeTreeItemResponse;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseTypeService
 * @projectName web-api
 * @date 2019/10/25 12:08
 */
public interface CourseTypeService {
    /**
    *
    * 方法功能描述: 根据id列表获取分类名称 分隔符默认为,
    *
    * @author yanshaowen
    * @date 2019/10/25 12:11
    * @param courseTypeIdList   id
    * @return
    * @throws
    */
    String getName(List<Integer> courseTypeIdList);
    /**
     *
     * 方法功能描述: 根据id列表获取分类名称
     *
     * @author yanshaowen
     * @date 2019/10/25 12:11
     * @param courseTypeIdList   id
     * @param character          分隔符
     * @return
     * @throws
     */
    String getName(List<Integer> courseTypeIdList, String character);

    /**
     *
     * 方法功能描述: 根据id列表获取分类名称
     *
     * @author yanshaowen
     * @date 2019/10/25 12:11
     * @param courseTypeId   id
     * @return
     * @throws
     */
    String getName(Integer courseTypeId);

    /**
     *
     * 方法功能描述: 根据id列表获取分类名称 分隔符默认为,
     *
     * @author yanshaowen
     * @date 2019/10/25 12:11
     * @param courseTypeIdList   id
     * @return
     * @throws
     */
    Map<Integer, String> getNameMap(List<Integer> courseTypeIdList);

    /**
     *
     * 方法功能描述: 根据id列表获取分类map对象
     *
     * @author yanshaowen
     * @date 2019/10/25 12:11
     * @param courseTypeId   id
     * @return
     * @throws
     */
    Map<Integer, String> getNameMap(Integer courseTypeId);

    /**
     *
     * 方法功能描述: 返回树形的课程分类
     *
     * @author yanshaowen
     * @date 2019/11/1 11:21
     * @param * @param
     * @return
     * @throws
     */
    List<CourseTypeTreeItemResponse> treeByFilterNotPage();

    /**
     *
     * 方法功能描述: 返回树形的课程分类
     *
     * @author yanshaowen
     * @date 2019/11/1 11:21
     * @param * @param
     * @return
     * @throws
     */
    List<CourseTypeListItemResponse> listByFilterNotPage(List<Integer> idList);
}
