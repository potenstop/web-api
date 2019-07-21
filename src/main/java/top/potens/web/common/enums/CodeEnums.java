package top.potens.web.common.enums;

/**
 * Created by wenshao on 2019/6/16.
 */
public enum CodeEnums {
    SUC("0", "成功"),
    PARAM_ERROR("10000", "参数不匹配"),
    MEMBER_NOT_FOUND("10100", "用户不存在"),
    MEMBER_EXIST_MORE("10101", "用户已经存在"),
    MEMBER_EXIST("10102", "用户已经存在"),
    MEMBER_MOBILE_INPUT_ERROR("10103", "手机号输入错误"),
    MEMBER_MAIL_INPUT_ERROR("10104", "邮箱输入错误"),
    CODE_COMPILER_ERROR("10105", "执行失败"),
    CITY_NOT_FOUND("10106", "城市没有找到"),
    CHANNEL_CODE_NOT_FOUND("10107", "渠道码没有找到"),
    CONTENT_EXIST("10108", "内容已经存在"),
    CONTENT_ZONE_NOT_FOUND("10109", "分区没有找到");

    private String msg;
    private String code;

    CodeEnums(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}