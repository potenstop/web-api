package top.potens.web.common.enums;

/**
 * Created by wenshao on 2019/6/16.
 */
public enum CodeEnums {
    SUC("0", "成功"),
    MEMBER_NOT_FOUND("10000", "用户不存在");


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