package top.potens.wechat.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AuthController
 * @projectName web-api
 * @date 2019/12/22 7:36
 */
@RestController
@RequestMapping("/auth")
@Api(description = "认证授权接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    @Value("${wxmp.app.id}")
    private String wxmpAppId;
//    private String wxmpCallback = "http://open.potens.top/wechat/auth/callback";
    private String wxmpCallback = "http://open.potens.top";

    @GetMapping("/redirect")
    public void redirectWxmp(HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize")
                .append("?")
                .append("appid=")
                .append(wxmpAppId)
                .append("&redirect_uri=")
                .append(URLEncoder.encode(wxmpCallback, "GBK"))
                .append("&response_type=code&scope=snsapi_base&state=hello")
                .append("#wechat_redirect");

        response.sendRedirect(sb.toString());
    }
}
