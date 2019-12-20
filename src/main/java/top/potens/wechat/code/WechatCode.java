package top.potens.wechat.code;

import top.potens.framework.annotation.ResultMessage;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ProblemCode
 * @projectName web-api
 * @date 2019/12/15 12:02
 */
public class WechatCode {
    @ResultMessage("获取token失败")
    public static final String TOKEN_API_EXCEPTION = "80000";
    @ResultMessage("获取token请求微信失败:{}")
    public static final String TOKEN_API_ERROR = "80001";
}
