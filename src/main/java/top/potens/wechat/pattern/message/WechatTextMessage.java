package top.potens.wechat.pattern.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import top.potens.wechat.common.constant.WechatMessageTypeConstant;
import top.potens.wechat.request.WechatMessageBaseRequest;
import top.potens.wechat.request.WechatMessagePostRequest;
import top.potens.wechat.request.WechatStandardTextMessageRequest;
import top.potens.wechat.response.WechatMessageBasicResponse;
import top.potens.wechat.response.WechatTextMessageResponse;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatTextMessage
 * @projectName web-api
 * @date 2019/12/23 17:52
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WechatTextMessage extends AbstractTemplateMessage<WechatStandardTextMessageRequest> {
    @Override
    public WechatStandardTextMessageRequest newInstance() {
        return new WechatStandardTextMessageRequest();
    }

    @Override
    public WechatStandardTextMessageRequest assembleConcrete() {
        WechatStandardTextMessageRequest request = this.assembleCommonRequest();
        request.setMsgId(this.wechatMessagePostRequest.getMsgId());
        request.setContent(this.wechatMessagePostRequest.getContent());
        return request;
    }

    @Override
    public WechatMessageBasicResponse response(WechatStandardTextMessageRequest request) {
        WechatTextMessageResponse wechatTextMessageResponse = this.assembleCommonResponse(WechatTextMessageResponse.class);
        wechatTextMessageResponse.setMsgType(WechatMessageTypeConstant.OutType.TEXT);
        wechatTextMessageResponse.setContent("你好");
        return wechatTextMessageResponse;
    }
}
