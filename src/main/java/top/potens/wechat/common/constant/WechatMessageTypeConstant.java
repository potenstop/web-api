package top.potens.wechat.common.constant;

import java.util.HashMap;

/**
 * 功能描述: 微信消息类型
 *
 * @author yanshaowen
 * @className WechatMessageTypeConstant
 * @projectName web-api
 * @date 2019/12/23 11:32
 */
public class WechatMessageTypeConstant {
    /**
     * 对外的类型 微信的类型
     */
    public static class OutType {
        public final static String TEXT = "text";
        public final static String IMAGE = "image";
        public final static String VOICE = "voice";
        public final static String VIDEO = "video";
        public final static String SHORT_VIDEO = "shortvideo";
        public final static String LOCATION = "location";
        public final static String LINK = "link";

        public final static String EVENT = "event";

        public final static String EVENT_SUBSCRIBE = "subscribe";
        public final static String EVENT_UNSUBSCRIBE = "unsubscribe";
        public final static String EVENT_SCAN = "SCAN";
        public final static String EVENT_LOCATION = "LOCATION";
        public final static String EVENT_CLICK = "CLICK";
        public final static String EVENT_VIEW = "VIEW";

    }

    /**
     * 对内的类型 系统的类型
     */
    public static class InType {
        public final static Integer TEXT = 1;
        public final static Integer IMAGE = 2;
        public final static Integer VOICE = 3;
        public final static Integer VIDEO = 4;
        public final static Integer SHORT_VIDEO = 5;
        public final static Integer LOCATION = 6;
        public final static Integer LINK = 7;
        public final static Integer SUBSCRIBE = 100;
        public final static Integer UNSUBSCRIBE = 101;
        public final static Integer REPORT_LOCATION = 102;
        public final static Integer SCAN = 103;
        public final static Integer CLICK = 104;
        public final static Integer VIEW = 105;
    }
}
