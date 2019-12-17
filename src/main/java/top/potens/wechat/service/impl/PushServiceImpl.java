package top.potens.wechat.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.util.StringUtil;
import top.potens.wechat.service.PushService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className PushServiceImpl
 * @projectName web-api
 * @date 2019/12/17 16:32
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PushServiceImpl implements PushService {
    @Override
    public String pushCheckMessageToken(String signature, String echostr, String nonce, String timestamp) {
        List<String> result = new ArrayList<>();
        result.add("Wendi_1209");
        result.add(timestamp);
        result.add(nonce);
        Collections.sort(result);
        String sha1String = DigestUtils.sha1Hex(StringUtil.join(result, ""));
        if (sha1String.equals(signature)) {
            return echostr;
        } else {
            return "";
        }
    }
}
