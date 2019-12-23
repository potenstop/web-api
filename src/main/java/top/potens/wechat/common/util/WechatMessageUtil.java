package top.potens.wechat.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import top.potens.framework.log.App;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.wechat.aec.wechat.WXBizMsgCrypt;
import top.potens.wechat.request.WechatMessagePostRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

        // 得到XML的根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        // 判断又没有子元素列表
        if (elementList.size() == 0) {
            map.put(root.getName(), root.getText());
        } else {
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(map, WechatMessagePostRequest.class);
    }
}
