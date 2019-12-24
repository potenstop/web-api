package top.potens.wechat.pattern.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
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
        WechatStandardTextMessageRequest request = this.assembleCommon();
        request.setMsgId(this.wechatMessagePostRequest.getMsgId());
        request.setContent(this.wechatMessagePostRequest.getContent());
        return request;
    }

    @Override
    public String response(WechatStandardTextMessageRequest request) {
        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + request.getOpenId() +"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + request.getToUserName() + "]]></FromUserName>\n" +
                "  <CreateTime>"+ request.getCreateTime() + "</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[你好]]></Content>\n" +
                "</xml>";
    }
}
