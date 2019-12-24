package top.potens.wechat.service.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
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
import top.potens.wechat.pattern.message.WechatSubscribeMessage;
import top.potens.wechat.pattern.message.WechatTextMessage;
import top.potens.wechat.pattern.message.WechatUnsubscribeMessage;
import top.potens.wechat.request.WechatMessageBaseRequest;
import top.potens.wechat.request.WechatMessagePostRequest;
import top.potens.wechat.service.PushService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    private ThreadPoolExecutor pushExecutor = new ThreadPoolExecutor(5, 20, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5000), new ThreadFactoryBuilder().setNameFormat("push-%d").build());
    @Value("${wxmp.app.id}")
    private String wxmpAppId;
    @Value("${wxmp.app.encodingAesKey}")
    private String wxmpEncodingAesKey;
    @Value("${wxmp.app.secret}")
    private String wxmpAppSecrect;
    @Value("${wxmp.app.token}")
    private String wxmpToken;
    private final WechatTextMessage wechatTextMessage;
    private final WechatSubscribeMessage wechatSubscribeMessage;
    private final WechatUnsubscribeMessage wechatUnsubscribeMessage;

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
        AbstractTemplateMessage abstractTemplateMessage = null;
        AppLogger.debug("处理微信消息 选择模板 type:[{}]", type);
        if (WechatMessageTypeConstant.InType.TEXT.equals(type)) {
            abstractTemplateMessage = wechatTextMessage;
        } else if (WechatMessageTypeConstant.InType.SUBSCRIBE.equals(type)) {
            abstractTemplateMessage = wechatSubscribeMessage;
        } else if (WechatMessageTypeConstant.InType.UNSUBSCRIBE.equals(type)) {
            abstractTemplateMessage = wechatUnsubscribeMessage;
        } else {
            throw new ApiException("100", "消息类型错误");
        }
        return abstractTemplateMessage;
    }

    private void syncHandleMessage (HttpServletRequest request) {
        pushExecutor.execute(() -> {

        });
    }
    @Override
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response) {
        String responseStr = "success";
        try {
            // 默认返回的文本消息内容
            // 调用消息工具类WechatMessageUtil解析微信发来的xml格式的消息
            WechatMessagePostRequest wechatMessagePostRequest = WechatMessageUtil.parseXml(request, wxmpToken, this.wxmpEncodingAesKey, this.wxmpAppId);
            AppLogger.info("微信消息开始处理 wechatMessagePostRequest:[{}]", JSON.toJSONString(wechatMessagePostRequest));
            AbstractTemplateMessage template = getTemplate(wechatMessagePostRequest);
            responseStr = template.start(wechatMessagePostRequest);
        } catch (Exception e) {
            AppLogger.error("微信消息开始处理 系统出错 message:[{}]", e, e.getMessage());
        }
        return responseStr;
    }
}
