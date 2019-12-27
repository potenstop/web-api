package top.potens.wechat.pattern.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.web.bmo.UserMoreAuthBo;
import top.potens.web.service.UserService;
import top.potens.wechat.common.constant.WechatMessageTypeConstant;
import top.potens.wechat.request.WechatMessageBaseRequest;
import top.potens.wechat.request.WechatMessagePostRequest;
import top.potens.wechat.response.WechatMessageBasicResponse;

import java.util.Date;
import java.util.HashMap;

/**
 * 功能描述: 抽象的消息模板类
 *
 * @author yanshaowen
 * @className AbstractTemplateMessage
 * @projectName web-api
 * @date 2019/12/23 16:48
 */
@Component
@Scope("prototype")
abstract public class AbstractTemplateMessage<Request extends WechatMessageBaseRequest> {
    @Autowired
    private UserService userService;
    protected WechatMessagePostRequest wechatMessagePostRequest;
    protected UserMoreAuthBo userMoreAuthBo;
    /**
     *
     * 方法功能描述: 微信的消息类型转换为内部系统的消息类型
     *
     * @author yanshaowen
     * @date 2019/12/23 11:51
     * @param msgType    微信的msgType
     * @param eventType  微信的事件类型
     * @return
     * @throws
     */
    public static Integer outTypeToInType (String msgType, String eventType) {

        HashMap<String, Integer> eventMap = new HashMap<>(16);
        eventMap.put(WechatMessageTypeConstant.OutType.EVENT_SUBSCRIBE, WechatMessageTypeConstant.InType.SUBSCRIBE);
        eventMap.put(WechatMessageTypeConstant.OutType.EVENT_UNSUBSCRIBE, WechatMessageTypeConstant.InType.UNSUBSCRIBE);
        eventMap.put(WechatMessageTypeConstant.OutType.EVENT_SCAN, WechatMessageTypeConstant.InType.SCAN);
        eventMap.put(WechatMessageTypeConstant.OutType.EVENT_LOCATION, WechatMessageTypeConstant.InType.REPORT_LOCATION);
        eventMap.put(WechatMessageTypeConstant.OutType.EVENT_CLICK, WechatMessageTypeConstant.InType.CLICK);
        eventMap.put(WechatMessageTypeConstant.OutType.EVENT_VIEW, WechatMessageTypeConstant.InType.VIEW);
        HashMap<String, Integer> standardMap = new HashMap<>();
        standardMap.put(WechatMessageTypeConstant.OutType.TEXT, WechatMessageTypeConstant.InType.TEXT);
        standardMap.put(WechatMessageTypeConstant.OutType.IMAGE, WechatMessageTypeConstant.InType.IMAGE);
        standardMap.put(WechatMessageTypeConstant.OutType.VOICE, WechatMessageTypeConstant.InType.VOICE);
        standardMap.put(WechatMessageTypeConstant.OutType.VIDEO, WechatMessageTypeConstant.InType.VIDEO);
        standardMap.put(WechatMessageTypeConstant.OutType.SHORT_VIDEO, WechatMessageTypeConstant.InType.SHORT_VIDEO);
        standardMap.put(WechatMessageTypeConstant.OutType.LOCATION, WechatMessageTypeConstant.InType.LOCATION);
        standardMap.put(WechatMessageTypeConstant.OutType.LINK, WechatMessageTypeConstant.InType.LINK);
        if (WechatMessageTypeConstant.OutType.EVENT.equals(msgType)) {
            // 事件消息
            if (eventMap.containsKey(eventType)) {
                return eventMap.get(eventType);
            }
        } else {
            // 主动消息
            if (standardMap.containsKey(msgType)) {
                return standardMap.get(msgType);
            }
        }
        return null;
    }
    /**
    *
    * 方法功能描述: 构建通用的request
    *
    * @author yanshaowen
    * @date 2019/12/23 19:26
    * @param * @param
    * @return
    * @throws
    */
    protected Request assembleCommonRequest() {
        Request request = newInstance();
        request.setOpenId(this.wechatMessagePostRequest.getOpenId());
        request.setCreateTime(this.wechatMessagePostRequest.getCreateTime());
        request.setToUserName(this.wechatMessagePostRequest.getToUserName());
        if (WechatMessageTypeConstant.OutType.EVENT.equals(this.wechatMessagePostRequest.getMsgType())) {
            request.setClassify(1);
        } else {
            request.setClassify(2);
        }
        Integer type = outTypeToInType(this.wechatMessagePostRequest.getMsgType(), this.wechatMessagePostRequest.getEvent());
        request.setMsgType(type);
        return request;
    }

    /**
     *
     * 方法功能描述: 构建通用的request
     *
     * @author yanshaowen
     * @date 2019/12/23 19:26
     * @param * @param
     * @return
     * @throws
     */
    protected <Response extends WechatMessageBasicResponse> Response assembleCommonResponse(Class<Response> targetClass) {
        try {
            Response target = targetClass.newInstance();
            target.setFromUserName(wechatMessagePostRequest.getToUserName());
            target.setCreateTime(wechatMessagePostRequest.getCreateTime());
            target.setToUserName(wechatMessagePostRequest.getOpenId());
            return target;
        } catch (Exception e) {
            throw new RuntimeException("创建公共响应失败" + targetClass);
        }
    }

    public String start(WechatMessagePostRequest wechatMessagePostRequest) {
        AppLogger.debug("处理微信消息 公共处理 wechatMessagePostRequest:[{}]", JSON.toJSONString(wechatMessagePostRequest));
        this.wechatMessagePostRequest = wechatMessagePostRequest;
        // 创建用户
        userMoreAuthBo = userService.wxmpLogin(wechatMessagePostRequest.getToUserName(), wechatMessagePostRequest.getOpenId());
        // 构建 base request
        Request request = this.assembleConcrete();
        // 响应消息
        WechatMessageBasicResponse response = this.response(request);
        AppLogger.debug("处理微信消息 公共处理 处理结束 response:[{}]", JSON.toJSONString(response));
        if (response == null) {
            return "success";
        }
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            AppLogger.error("处理微信消息 转换xml失败", e);
            return "success";
        }
    }

    /**
     *
     * 方法功能描述: 创建对象
     *
     * @author yanshaowen
     * @date 2019/12/23 18:12
     * @return
     * @throws
     */
    abstract public Request newInstance();
    /**
    *
    * 方法功能描述: 构建业务的request
    *
    * @author yanshaowen
    * @date 2019/12/23 18:12
    * @return
    * @throws
    */
    abstract public Request assembleConcrete();
    /**
    *
    * 方法功能描述:
    *
    * @author yanshaowen
    * @date 2019/12/23 17:57
    * @param request
    * @return
    * @throws
    */
    abstract public WechatMessageBasicResponse response(Request request);
}
