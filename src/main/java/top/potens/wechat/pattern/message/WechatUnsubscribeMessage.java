package top.potens.wechat.pattern.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.wechat.request.WechatEventUnsubscribeMessageRequest;
import top.potens.wechat.response.WechatMessageBasicResponse;

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
        return this.assembleCommonRequest();
    }

    @Override
    public WechatMessageBasicResponse response(WechatEventUnsubscribeMessageRequest request) {
        AppLogger.info("有用户取消了关注 request:[{}]", JSON.toJSONString(request));
        return null;
    }
}
