package top.potens.wechat.controller;

import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.potens.cms.service.QiniuService;
import top.potens.framework.model.ApiResult;
import top.potens.framework.util.CollectionUtil;
import top.potens.framework.util.StringUtil;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className QiniuController
 * @projectName web-api
 * @date 2019/9/25 19:41
 */
@RestController
@RequestMapping("/push")
@Api(description = "微信主动推送的接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PushController {

    /**
    *
    * 方法功能描述: https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
    *
    * @author yanshaowen
    * @date 2019/12/17 9:11
    * @param * @param signature
    * @param echostr
    * @param nonce
    * @param timestamp
    * @return
    * @throws
    */
    @GetMapping("/message")
    @ApiOperation(value = "验证消息的确来自微信服务器")
    public String pushCheckMessageToken(
            @ApiParam(value = "signature", example = "1") @RequestParam @NotNull String signature,
            @ApiParam(value = "echostr", example = "1") @RequestParam @NotNull String echostr,
            @ApiParam(value = "nonce", example = "1") @RequestParam @NotNull String nonce,
            @ApiParam(value = "timestamp", example = "1") @RequestParam @NotNull String timestamp
    ) {


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

    /**
     *
     * 方法功能描述: https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
     *
     * @author yanshaowen
     * @date 2019/12/17 9:11
     * @param * @param signature
     * @param echostr
     * @param nonce
     * @param timestamp
     * @return
     * @throws
     */
    @PostMapping("/message")
    @ApiOperation(value = "接受微信的消息推送")
    public String receiveMessage(
            @ApiParam(value = "signature", example = "1") @RequestParam @NotNull String signature,
            @ApiParam(value = "echostr", example = "1") @RequestParam @NotNull String echostr,
            @ApiParam(value = "nonce", example = "1") @RequestParam @NotNull String nonce,
            @ApiParam(value = "timestamp", example = "1") @RequestParam @NotNull Long timestamp
    ) {
        return echostr;
    }
}
