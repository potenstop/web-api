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
import top.potens.framework.log.AppLogger;
import top.potens.framework.model.ApiResult;
import top.potens.framework.util.CollectionUtil;
import top.potens.framework.util.StringUtil;
import top.potens.wechat.service.PushService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private final PushService pushService;

    @GetMapping("/message")
    @ApiOperation(value = "验证消息的确来自微信服务器")
    public String pushCheckMessageToken(
            @ApiParam(value = "signature", example = "1") @RequestParam @NotNull String signature,
            @ApiParam(value = "echostr", example = "1") @RequestParam @NotNull String echostr,
            @ApiParam(value = "nonce", example = "1") @RequestParam @NotNull String nonce,
            @ApiParam(value = "timestamp", example = "1") @RequestParam @NotNull String timestamp
    ) {
        return pushService.pushCheckMessageToken(signature, echostr, nonce, timestamp);
    }

    @PostMapping("/message")
    @ApiOperation(value = "接受微信的消息推送")
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response) {
        AppLogger.info("----------------开始处理微信发过来的消息------------------");
        String respXml = pushService.receiveMessage(request, response);
        return respXml;
    }
}
