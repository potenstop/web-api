package top.potens.wechat.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.framework.util.StringUtil;
import top.potens.wechat.common.constant.WechatMessageTypeConstant;
import top.potens.wechat.common.util.WechatMessageUtil;
import top.potens.wechat.pattern.message.AbstractTemplateMessage;
import top.potens.wechat.pattern.message.WechatTextMessage;
import top.potens.wechat.request.WechatMessageBaseRequest;
import top.potens.wechat.request.WechatMessagePostRequest;
import top.potens.wechat.service.PushService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className PushServiceImpl
 * @projectName web-api
 * @date 2019/12/17 16:32
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PushServiceImpl implements PushService {
    @Value("${wxmp.app.id}")
    private String wxmpAppId;
    @Value("${wxmp.app.encodingAesKey}")
    private String wxmpEncodingAesKey;
    @Value("${wxmp.app.secret}")
    private String wxmpAppSecrect;
    @Value("${wxmp.app.token}")
    private String wxmpToken;

    @Override
    public String pushCheckMessageToken(String signature, String echostr, String nonce, String timestamp) {
        List<String> result = new ArrayList<>();
        result.add(wxmpToken);
        result.add(timestamp);
        result.add(nonce);
        Collections.sort(result);
        String sha1String = DigestUtils.sha1Hex(StringUtil.join(result, ""));
        if (sha1String.equals(signature)) {
            return echostr;
        } else {
            return "";
        }
    }

    private AbstractTemplateMessage getTemplate(WechatMessagePostRequest wechatMessagePostRequest) {
        Integer type = AbstractTemplateMessage.outTypeToInType(wechatMessagePostRequest.getMsgType(), wechatMessagePostRequest.getEvent());
        if (type == null) {
            throw new ApiException("100", "消息类型错误");
        }
        AbstractTemplateMessage abstractTemplateMessage = new WechatTextMessage();;
        if (WechatMessageTypeConstant.InType.TEXT.equals(type)) {
            abstractTemplateMessage = new WechatTextMessage();
        }
        return abstractTemplateMessage;

    }

    @Override
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response) {
        AppLogger.info("------------微信消息开始处理-------------");
        // 返回给微信服务器的消息,默认为null
        String respMessage = null;
        try {
            // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
            response.setCharacterEncoding("UTF-8");
            // 默认返回的文本消息内容
            // 调用消息工具类WechatMessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
            WechatMessagePostRequest wechatMessagePostRequest = WechatMessageUtil.parseXml(request, wxmpToken, this.wxmpEncodingAesKey, this.wxmpAppId);
            AppLogger.info("微信消息开始处理 wechatMessagePostRequest:[{}]", JSON.toJSONString(wechatMessagePostRequest));
            AbstractTemplateMessage template = getTemplate(wechatMessagePostRequest);
            String responseContent = template.start(wechatMessagePostRequest);
            // 转为xml 并加密

            respMessage = "111";
        } catch (Exception e) {
            AppLogger.error("系统出错 message:[{}]", e, e.getMessage());
            respMessage = null;
        } finally {

        }
        return respMessage;
    }
}
