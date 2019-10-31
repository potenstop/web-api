package top.potens.web.dao.db.ext;

import org.apache.ibatis.annotations.Param;
import top.potens.web.bmo.CourseInfoTypeBo;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseExMapper
 * @projectName web-api
 * @date 2019/10/31 14:23
 */
public interface CourseExMapper {

    /**
    *
    * 方法功能描述:
    *
    * @author yanshaowen
    * @date 2019/10/31 14:37
    * @param courseId
    * @param courseName
    * @param courseStairId
    * @param courseSecondId
    * @param courseThreeId
    * @return
    * @throws
    */
    List<Integer> selectCourseIdList(
            @Param("courseId") Integer courseId,
            @Param("courseName") String courseName,
            @Param("courseStairId") Integer courseStairId,
            @Param("courseSecondId") Integer courseSecondId,
            @Param("courseThreeId") Integer courseThreeId);

    /**
    *
    * 方法功能描述:
    *
    * @author yanshaowen
    * @date 2019/10/31 17:26
    * @param courseIdList
    * @return
    * @throws
    */
    List<CourseInfoTypeBo> selectCourseListByIds(@Param("courseIdList") List<Integer> courseIdList);
}
