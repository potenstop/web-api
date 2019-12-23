package top.potens.wechat.pattern.message;

import top.potens.wechat.request.WechatMessageBaseRequest;
import top.potens.wechat.request.WechatMessagePostRequest;
import top.potens.wechat.request.WechatStandardTextMessageRequest;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatTextMessage
 * @projectName web-api
 * @date 2019/12/23 17:52
 */
public class WechatTextMessage extends AbstractTemplateMessage<WechatStandardTextMessageRequest> {
    @Override
    public WechatStandardTextMessageRequest newInstance() {
        return new WechatStandardTextMessageRequest();
    }

    @Override
    public WechatStandardTextMessageRequest assembleConcrete() {
        WechatStandardTextMessageRequest request = this.assembleCommon();
        request.setMsgId(this.wechatMessagePostRequest.getMsgId());
        request.setContent(this.wechatMessagePostRequest.getContent());
        return request;
    }

    @Override
    public String response(WechatStandardTextMessageRequest request) {
        return "111";
    }
}
