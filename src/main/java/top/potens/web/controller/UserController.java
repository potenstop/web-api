package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.potens.framework.annotation.UserAuthToken;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.framework.model.ApiResult;
import top.potens.framework.model.TokenUser;
import top.potens.framework.serialization.JSON;
import top.potens.framework.util.TokenUtil;
import top.potens.web.bmo.UserMoreAuthBo;
import top.potens.web.code.CommonCode;
import top.potens.web.common.constant.ChannelConstant;
import top.potens.web.model.Channel;
import top.potens.web.model.UserAuth;
import top.potens.web.request.UserRegisterRequest;
import top.potens.web.response.UserAuthBaseResponse;
import top.potens.web.service.UserService;
import top.potens.web.service.logic.ContentCacheService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserController
 * @projectName web-api
 * @date 2019/7/5 13:36
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户管理操作")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final ContentCacheService contentCacheService;

    private UserAuthBaseResponse convertUserInfo(UserMoreAuthBo userMoreAuthBo) {
        UserAuthBaseResponse userAuthBaseResponse = new UserAuthBaseResponse();
        BeanUtils.copyProperties(userMoreAuthBo, userAuthBaseResponse);
        ArrayList<UserAuthBaseResponse.UserAuthResponse> userAuthRespons = new ArrayList<>();
        userMoreAuthBo.getUserAuthList().forEach(userAuth -> {
            UserAuthBaseResponse.UserAuthResponse userAuthResponse = userAuthBaseResponse.createUserAuthResponse();
            BeanUtils.copyProperties(userAuth, userAuthResponse);
            userAuthRespons.add(userAuthResponse);
        });
        userAuthBaseResponse.setUserAuthList(userAuthRespons);
        return userAuthBaseResponse;
    }

    @GetMapping("/info")
    @ApiOperation(value = "按id获取一条记录")
    @UserAuthToken
    public ApiResult<UserAuthBaseResponse> userInfo(TokenUser tokenUser) {
        AppLogger.info("获取用户信息 userId:[{}]", tokenUser.getUserId());
        UserMoreAuthBo userMoreAuthBo = userService.queryDetailById(tokenUser.getUserId());
        ApiResult<UserAuthBaseResponse> apiResult = new ApiResult<>();
        UserAuthBaseResponse userAuthBaseResponse = convertUserInfo(userMoreAuthBo);
        apiResult.setData(userAuthBaseResponse);
        AppLogger.info("获取用户信息 userId:[{}] [apiResult:{}]", tokenUser.getUserId(), JSON.toJSONString(apiResult));
        return apiResult;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册接口")
    public ApiResult<UserAuthBaseResponse> userRegister(@RequestBody @Valid UserRegisterRequest request) {
        AppLogger.info("注册用户接口 request:[{}]", JSON.toJSONString(request));
        ApiResult<UserAuthBaseResponse> apiResult = new ApiResult<>();
        // 1参数校验
        userService.validateRegisterParams(request);
        Integer userId = null;
        // 2 插入数据
        if (ChannelConstant.ChannelCode.SELF_TEL.equals(request.getChannelCode())) {
            userId = userService.insertByMobile(request);
        } else if (ChannelConstant.ChannelCode.SELF_MAIL.equals(request.getChannelCode())) {
            userId = userService.insertByMail(request);
        } else {
            AppLogger.error("注册用户接口 未知的注册类型 channelCode:[{}]", request.getChannelCode());
            throw new ApiException(CommonCode.PARAM_ERROR);
        }
        // 3 查询用户数据
        UserMoreAuthBo userMoreAuthBo = userService.queryDetailById(userId);
        UserAuthBaseResponse userAuthBaseResponse = convertUserInfo(userMoreAuthBo);
        apiResult.setData(userAuthBaseResponse);
        AppLogger.info("注册用户接口 正常返回 request:[{}] apiResult:[{}]", JSON.toJSONString(request), JSON.toJSONString(apiResult));
        return apiResult;
    }
    @GetMapping("/visitor/login")
    @ApiOperation(value = "游客用户登录注册接口")
    public ApiResult<UserAuthBaseResponse> userVisitorLogin(
            @ApiParam(value = "uuid", example = "1") @RequestParam(required = true) @NotNull String uuid
    ) {
        AppLogger.info("游客注册登录接口 uuid:[{}]", uuid);
        Channel channel = contentCacheService.getChannelByCode(ChannelConstant.ChannelCode.SELF_VISITOR);
        ApiResult<UserAuthBaseResponse> apiResult = new ApiResult<>();
        UserAuth userAuth = userService.existAuth(channel.getChannelId(), uuid, null);
        Integer userId = null;
        if (userAuth == null) {
            // 根据uuid注册游客用户
            userId = userService.insertByUuid(uuid);
        } else {
            userId = userAuth.getUserId();
        }
        UserMoreAuthBo userMoreAuthBo = userService.queryDetailById(userId);
        UserAuthBaseResponse userAuthBaseResponse = convertUserInfo(userMoreAuthBo);
        apiResult.setData(userAuthBaseResponse);
        AppLogger.info("游客注册登录接口 正常返回 uuid:[{}] apiResult:[{}]", uuid, JSON.toJSONString(apiResult));
        return apiResult;
    }
    @GetMapping("/ldap/login")
    @ApiOperation(value = "ldap用户登录")
    public ApiResult<String> ldapLogin(
            @ApiParam(value = "username", example = "1") @RequestParam(required = true) @NotNull String username,
            @ApiParam(value = "password", example = "1") @RequestParam(required = true) @NotNull String password
    ) {
        Channel channel = contentCacheService.getChannelByCode(ChannelConstant.ChannelCode.SELF_LDAP);
        ApiResult<String> result = new ApiResult<>();
        result.setData(userService.ldapLogin(channel, username, password));
        return result;
    }
    @GetMapping("/ldap/test")
    @ApiOperation(value = "test")
    @UserAuthToken
    public ApiResult<Boolean> ldapLogin(TokenUser tokenUser) {
        AppLogger.info("a:{}", JSON.toJSONString(tokenUser));
        return new ApiResult<>();
    }

}
