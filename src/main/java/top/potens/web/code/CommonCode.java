package top.potens.web.code;

import top.potens.framework.response.ResultMessage;

/**
 * 功能描述: 通用code码  10000-19999
 *
 * @author yanshaowen
 * @className CommonCode
 * @projectName web-api
 * @date 2019/8/18 13:17
 */
public class CommonCode {

    @ResultMessage("参数不匹配")
    public static final String PARAM_ERROR = "10001";

    @ResultMessage("调用失败")
    public static final String REMOTE_CALL_ERROR = "10002";

    @ResultMessage("代码执行失败:{}")
    public static final String CODE_COMPILER_ERROR = "10100";

    @ResultMessage("城市没有找到")
    public static final String CITY_NOT_FOUND = "10101";

    @ResultMessage("渠道码没有找到")
    public static final String CHANNEL_CODE_NOT_FOUND = "10102";


}
