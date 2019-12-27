package top.potens.wechat.pattern.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.potens.wechat.common.constant.WechatMessageTypeConstant;
import top.potens.wechat.request.WechatEventSubscribeMessageRequest;
import top.potens.wechat.request.WechatStandardTextMessageRequest;
import top.potens.wechat.response.WechatMessageBasicResponse;
import top.potens.wechat.response.WechatTextMessageResponse;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatSubscribeMessage
 * @projectName web-api
 * @date 2019/12/24 10:45
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WechatSubscribeMessage extends AbstractTemplateMessage<WechatEventSubscribeMessageRequest> {
    @Value("${wxmp.welcome.tip:欢迎关注}")
    private String wxmpWelcomeTip;
    @Override
    public WechatEventSubscribeMessageRequest newInstance() {
        return new WechatEventSubscribeMessageRequest();
    }

    @Override
    public WechatEventSubscribeMessageRequest assembleConcrete() {
        WechatEventSubscribeMessageRequest request = this.assembleCommonRequest();
        request.setTicket(this.wechatMessagePostRequest.getTicket());
        request.setEventKey(this.wechatMessagePostRequest.getEventKey());
        return request;
    }

    @Override
    public WechatMessageBasicResponse response(WechatEventSubscribeMessageRequest request) {
        WechatTextMessageResponse wechatTextMessageResponse = this.assembleCommonResponse(WechatTextMessageResponse.class);
        wechatTextMessageResponse.setMsgType(WechatMessageTypeConstant.OutType.TEXT);
        wechatTextMessageResponse.setContent(wxmpWelcomeTip);
        return wechatTextMessageResponse;
    }
}
