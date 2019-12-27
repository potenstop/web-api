package top.potens.wechat.common.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import top.potens.framework.log.AppLogger;
import top.potens.wechat.aec.wechat.WXBizMsgCrypt;
import top.potens.wechat.request.WechatMessagePostRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatMessageUtil
 * @projectName web-api
 * @date 2019/12/22 12:55
 */
public class WechatMessageUtil {

    public static String readAsChars(HttpServletRequest request) throws IOException {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } finally {
            if (null != br) {
                br.close();
            }
        }
        return sb.toString();
    }

    /***
     * 解析微信服务器发过来的xml格式的消息将其转换为request
     * @param request
     * @return
     * @throws Exception
     */
    public static WechatMessagePostRequest parseXml(HttpServletRequest request, String token, String encodingAesKey, String appId) throws Exception {
        // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        request.setCharacterEncoding("UTF-8");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        String msgSignature = request.getParameter("msg_signature");
        String context = readAsChars(request);
        AppLogger.info("解析微信的消息 开始解码 msgSignature:[{}] timestamp:[{}] nonce:[{}] context:[{}]", msgSignature, timestamp, nonce, context);
        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
        String xml = pc.decryptMsg(msgSignature, timestamp, nonce, context);
        AppLogger.info("解析微信的消息 解码完成 xml:[{}]", xml);
        XmlMapper xmlMapper = new XmlMapper();
        WechatMessagePostRequest wechatMessagePostRequest = xmlMapper.readValue(xml, WechatMessagePostRequest.class);
        return wechatMessagePostRequest;
    }
}
