package top.potens.wechat.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.framework.util.StringUtil;
import top.potens.wechat.common.util.WechatMessageUtil;
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
        result.add("Wendi_1209");
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

    @Override
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response) {
        AppLogger.info("------------微信消息开始处理-------------");
        // 返回给微信服务器的消息,默认为null

        String respMessage = null;

        try {
            // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
            request.setCharacterEncoding("UTF-8");
            // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
            response.setCharacterEncoding("UTF-8");
            // 默认返回的文本消息内容
            String respContent = null;
            // xml分析
            // 调用消息工具类WechatMessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
            Map<String, String> map = WechatMessageUtil.parseXml(request, wxmpToken, this.wxmpEncodingAesKey, this.wxmpAppId);
            AppLogger.info("map[{}]", JSON.toJSONString(map));
            respMessage = "111";
        } catch (Exception e) {
            e.printStackTrace();
            AppLogger.error("系统出错");
            System.err.println("系统出错");
            respMessage = null;
        } finally {

        }
        return respMessage;
    }
}
