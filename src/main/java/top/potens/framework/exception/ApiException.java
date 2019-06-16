package top.potens.framework.exception;

/**
 * Created by wenshao on 2019/6/16.
 */
public class ApiException extends RuntimeException {
    private String code;
    private String message;

    public ApiException() {
        super();
    }
    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiException(String code) {
        super();
        this.code = code;
        this.message = "";
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
