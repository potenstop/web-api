package top.potens.web.code;

import top.potens.framework.annotation.ResultMessage;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ProblemCode
 * @projectName web-api
 * @date 2019/12/15 12:02
 */
public class ProblemCode {
    @ResultMessage("选项id必须为数字")
    public static final String PROBLEM_OPTION_ID_IS_NUMBER = "70000";
}
