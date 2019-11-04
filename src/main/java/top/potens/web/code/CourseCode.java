package top.potens.web.code;

import top.potens.framework.annotation.ResultMessage;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseCode
 * @projectName web-api
 * @date 2019/11/1 12:16
 */
public class CourseCode {
    @ResultMessage("课程分类id错误")
    public static final String COURSE_TYPE_ID_ERROR = "40000";

    @ResultMessage("课程id不存在")
    public static final String COURSE_ID_NOT_FOUND = "40001";
}
