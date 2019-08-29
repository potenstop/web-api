package top.potens.framework.exception;

import top.potens.framework.response.ResultCodeInit;

/**
 * Created by wenshao on 2019/6/16.
 */
public class ApiException extends RuntimeException {
    private String code;
    private String message;

    public ApiException() {
        super("");
    }
    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public ApiException(String code, Object... args) {
        super(ResultCodeInit.getResultMsg(code, args));
        this.code = code;
        this.message = super.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
