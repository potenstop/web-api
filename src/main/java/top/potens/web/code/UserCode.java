package top.potens.web.code;

import top.potens.framework.annotation.ResultMessage;

/**
 * 功能描述: 用户code码 20000-29999
 *
 * @author yanshaowen
 * @className UserCode
 * @projectName web-api
 * @date 2019/8/18 9:17
 */
public class UserCode {
    @ResultMessage("用户不存在")
    public static final String USER_NOT_FOUND = "20001";

    @ResultMessage("用户已存在")
    public static final String USER_EXIST_MORE = "20002";

    @ResultMessage("手机号输入错误")
    public static final String USER_MOBILE_INPUT_ERROR = "20003";

    @ResultMessage("邮箱输入错误")
    public static final String USER_MAIL_INPUT_ERROR = "20004";

    @ResultMessage("用户名或密码错误")
    public static final String USERNAME_OR_PASSWORD_ERROR = "20005";
}