package top.potens.wechat.pattern.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import top.potens.wechat.request.WechatEventSubscribeMessageRequest;
import top.potens.wechat.request.WechatStandardTextMessageRequest;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatSubscribeMessage
 * @projectName web-api
 * @date 2019/12/24 10:45
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WechatSubscribeMessage extends AbstractTemplateMessage<WechatEventSubscribeMessageRequest> {
    @Override
    public WechatEventSubscribeMessageRequest newInstance() {
        return new WechatEventSubscribeMessageRequest();
    }

    @Override
    public WechatEventSubscribeMessageRequest assembleConcrete() {
        WechatEventSubscribeMessageRequest request = this.assembleCommon();
        request.setTicket(this.wechatMessagePostRequest.getTicket());
        request.setEventKey(this.wechatMessagePostRequest.getEventKey());
        return request;
    }

    @Override
    public String response(WechatEventSubscribeMessageRequest request) {

        return "122";
    }
}
