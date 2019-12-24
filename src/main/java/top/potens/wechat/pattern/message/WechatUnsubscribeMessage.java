package top.potens.wechat.pattern.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import top.potens.wechat.request.WechatEventUnsubscribeMessageRequest;

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
public class WechatUnsubscribeMessage extends AbstractTemplateMessage<WechatEventUnsubscribeMessageRequest> {
    @Override
    public WechatEventUnsubscribeMessageRequest newInstance() {
        return new WechatEventUnsubscribeMessageRequest();
    }

    @Override
    public WechatEventUnsubscribeMessageRequest assembleConcrete() {
        return this.assembleCommon();
    }

    @Override
    public String response(WechatEventUnsubscribeMessageRequest request) {
        return "122";
    }
}
