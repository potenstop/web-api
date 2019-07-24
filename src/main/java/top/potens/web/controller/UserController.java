package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.framework.model.ApiResult;
import top.potens.framework.serialization.JSON;
import top.potens.web.bmo.UserAuthInfoBO;
import top.potens.web.common.constant.ChannelConstant;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.model.Channel;
import top.potens.web.model.UserAuth;
import top.potens.web.request.UserRegisterRequest;
import top.potens.web.response.UserAuthBaseResponse;
import top.potens.web.service.Userervice;
import top.potens.web.service.logic.ContentCacheService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


/**
 * Created by wenshao on 2019/6/15.
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户管理操作")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final Userervice userervice;
    private final ContentCacheService contentCacheService;

    private UserAuthBaseResponse convertUserInfo(UserAuthInfoBO userAuthInfoBO) {
        UserAuthBaseResponse userAuthBaseResponse = new UserAuthBaseResponse();
        BeanUtils.copyProperties(userAuthInfoBO, userAuthBaseResponse);
        ArrayList<UserAuthBaseResponse.UserAuthResponse> userAuthRespons = new ArrayList<>();
        userAuthInfoBO.getUserAuthList().forEach(userAuth -> {
            UserAuthBaseResponse.UserAuthResponse userAuthResponse = userAuthBaseResponse.createUserAuthResponse();
            BeanUtils.copyProperties(userAuth, userAuthResponse);
            userAuthRespons.add(userAuthResponse);
        });
        userAuthBaseResponse.setUserAuthList(userAuthRespons);
        return userAuthBaseResponse;
    }

    @GetMapping("/by-id")
    @ApiOperation(value = "按id获取一条记录")
    public ApiResult<UserAuthBaseResponse> userById(
            @ApiParam(value = "userId", example = "1") @RequestParam(required = true) @NotNull Integer userId
    ) {
        AppUtil.info("按id获取user信息 userId:[{}]", userId);
        UserAuthInfoBO userAuthInfoBO = userervice.queryById(userId);
        ApiResult<UserAuthBaseResponse> apiResult = new ApiResult<>();
        UserAuthBaseResponse userAuthBaseResponse = convertUserInfo(userAuthInfoBO);
        apiResult.setData(userAuthBaseResponse);
        AppUtil.info("按id获取user信息 userId:[{}] [apiResult:{}]", userId, JSON.toJSONString(apiResult));
        return apiResult;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册接口")
    public ApiResult<UserAuthBaseResponse> userRegister(@RequestBody @Valid UserRegisterRequest request) {
        AppUtil.info("注册用户接口 request:[{}]", JSON.toJSONString(request));
        ApiResult<UserAuthBaseResponse> apiResult = new ApiResult<>();
        // 1参数校验
        userervice.validateRegisterParams(request);
        Integer userId = null;
        // 2 插入数据
        if (ChannelConstant.ChannelCode.SELF_TEL.equals(request.getChannelCode())) {
            userId = userervice.insertByMobile(request);
        } else if (ChannelConstant.ChannelCode.SELF_MAIL.equals(request.getChannelCode())) {
            userId = userervice.insertByMail(request);
        } else {
            AppUtil.error("注册用户接口 未知的注册类型 channelCode:[{}]", request.getChannelCode());
            throw new ApiException(CodeEnums.PARAM_ERROR.getCode(), CodeEnums.PARAM_ERROR.getMsg());
        }
        // 3 查询用户数据
        UserAuthInfoBO userAuthInfoBO = userervice.queryById(userId);
        UserAuthBaseResponse userAuthBaseResponse = convertUserInfo(userAuthInfoBO);
        apiResult.setData(userAuthBaseResponse);
        AppUtil.info("注册用户接口 正常返回 request:[{}] apiResult:[{}]", JSON.toJSONString(request), JSON.toJSONString(apiResult));
        return apiResult;
    }
    @GetMapping("/visitor/login")
    @ApiOperation(value = "游客用户登录注册接口")
    public ApiResult<UserAuthBaseResponse> userVisitorLogin(
            @ApiParam(value = "uuid", example = "1") @RequestParam(required = true) @NotNull String uuid
    ) {
        AppUtil.info("游客注册登录接口 uuid:[{}]", uuid);
        Channel channel = contentCacheService.getChannelByCode(ChannelConstant.ChannelCode.SELF_VISITOR);
        ApiResult<UserAuthBaseResponse> apiResult = new ApiResult<>();
        UserAuth userAuth = userervice.existAuth(channel.getChannelId(), uuid, null);
        Integer userId = null;
        if (userAuth == null) {
            // 根据uuid注册游客用户
            userId = userervice.insertByUuid(uuid);
        } else {
            userId = userAuth.getUserId();
        }
        UserAuthInfoBO userAuthInfoBO = userervice.queryById(userId);
        UserAuthBaseResponse userAuthBaseResponse = convertUserInfo(userAuthInfoBO);
        apiResult.setData(userAuthBaseResponse);
        AppUtil.info("游客注册登录接口 正常返回 uuid:[{}] apiResult:[{}]", uuid, JSON.toJSONString(apiResult));
        return apiResult;
    }

}
