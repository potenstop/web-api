package top.potens.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.potens.framework.model.ApiResult;
import top.potens.wechat.service.TokenService;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className TokenController
 * @projectName web-api
 * @date 2019/12/17 16:25
 */
@RestController
@RequestMapping("/token")
@Api(description = "token管理的接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenController {
    private final TokenService tokenService;
    @GetMapping("/wxmp")
    @ApiOperation(value = "获取h5的token")
    public ApiResult<String> getWxmpToken() {
        ApiResult<String> result = new ApiResult<>();
        result.setData(tokenService.getWxmpToken());
        return result;
    }
}
