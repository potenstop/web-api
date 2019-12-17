package top.potens.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.potens.cms.service.QiniuService;
import top.potens.framework.model.ApiResult;

import javax.validation.constraints.NotNull;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className QiniuController
 * @projectName web-api
 * @date 2019/9/25 19:41
 */
@RestController
@RequestMapping("/check")
@Api(description = "校验的接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckController {

    @GetMapping("/join/token")
    @ApiOperation(value = "接入流程的校验token")
    public String joinToken(
            @ApiParam(value = "signature", example = "1") @RequestParam @NotNull String signature,
            @ApiParam(value = "echostr", example = "1") @RequestParam @NotNull String echostr,
            @ApiParam(value = "nonce", example = "1") @RequestParam @NotNull String nonce,
            @ApiParam(value = "timestamp", example = "1") @RequestParam @NotNull Long timestamp
    ) {
        return echostr;
    }
}
