package top.potens.wechat.common.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import top.potens.framework.log.App;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.wechat.aec.wechat.WXBizMsgCrypt;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
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
    /**
     * 请求消息类型：文本
     */
    public final static String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public final static String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：语音
     */
    public final static String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：视频
     */
    public final static String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**
     * 请求消息类型：链接
     */
    public final static String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public final static String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：小视频
     */
    public final static String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

    /**
     * 请求消息类型：事件推送
     */
    public final static String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 返回消息类型：文本
     */
    public final static String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 消息返回类型：图片
     */
    public final static String RESP_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 消息返回类型:语音
     */
    public final static String RESP_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 消息返回类型：音乐
     */
    public final static String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 消息返回类型：图文
     */
    public final static String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 消息返回类型：视频
     */
    public final static String RESP_MESSAGE_TYPE_VIDEO = "video";

    /**
     * 事件类型:订阅
     */
    public final static String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：取消订阅
     */
    public final static String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：scan(关注用户扫描带参二维码)
     */
    public final static String EVENT_TYPE_SCAN = "scan";

    /**
     * 事件类型：location(上报地理位置)
     */
    public final static String EVENT_TYPE_LOCATION = "location";

    /**
     * 事件类型：CLICK(点击菜单拉取消息)
     */
    public final static String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 事件类型：VIEW(自定义菜单URl视图)
     */
    public final static String EVENT_TYPE_VIEW = "VIEW";

    /**
     * 事件类型：TEMPLATESENDJOBFINISH(模板消息送达情况提醒)
     */
    public final static String EVENT_TYPE_TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";


    /***
     * 解析微信服务器发过来的xml格式的消息将其转换为map
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request, String token, String encodingAesKey, String appId) throws Exception {
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        String msgSignature = request.getParameter("msg_signature");

        byte[] buffer = new byte[64*1024];
        InputStream in = request.getInputStream();
        int length = in.read(buffer);
        String encode = request.getCharacterEncoding();

        byte[] data = new byte[length];
        System.arraycopy(buffer, 0, data, 0, length);
        String context = new String(data, encode);
        AppLogger.info("------------- {}", context);

        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();
        // 从request中得到输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

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
        // 释放资源
        inputStream.close();
        // 解密
        if (map.containsKey("Encrypt")) {
            AppLogger.info("data === {} {} {} {} {}", msgSignature, timestamp, nonce, document.getText());
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, document.getText());
            AppLogger.info("result2 === {} ", result2);
        }

        return map;
    }
}
