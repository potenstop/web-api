package top.potens.wechat.dao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.potens.wechat.dao.client.response.WxmpTokenResponse;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatProxyClient
 * @projectName web-api
 * @date 2019/12/17 17:21
 */
@FeignClient(name = "wechat", url = "${web.connection.wechat.domain}")
public interface WechatProxyClient {
    /**
    *
    * 方法功能描述: 刷新token
    *
    * @author yanshaowen
    * @date 2019/12/18 11:03
    * @param * @param grantType
    * @param appId
    * @param secret
    * @return
    * @throws
    */
    @GetMapping("/cgi-bin/token")
    WxmpTokenResponse getWxmpToken(@RequestParam("grant_type") String grantType, @RequestParam("appid") String appId, @RequestParam("secret") String secret);
}
