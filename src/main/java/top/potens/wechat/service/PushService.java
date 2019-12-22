package top.potens.wechat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className PushService
 * @projectName web-api
 * @date 2019/12/17 16:29
 */
public interface PushService {
    /**
    *
    * 方法功能描述: 校验加密的字符和传入的是否一致
    *  https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
    * @author yanshaowen
    * @date 2019/12/17 16:31
    * @param signature
    * @param echostr
    * @param nonce
    * @param timestamp
    * @return
    * @throws
    */
    String pushCheckMessageToken(String signature, String echostr, String nonce, String timestamp);

    String receiveMessage(HttpServletRequest request,
                   HttpServletResponse response);
}
