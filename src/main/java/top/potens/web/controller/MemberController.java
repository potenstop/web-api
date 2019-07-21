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
import top.potens.web.bmo.MemberAuthInfoBO;
import top.potens.web.common.constant.ChannelConstant;
import top.potens.web.common.constant.MemberConstant;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.model.Channel;
import top.potens.web.model.MemberAuth;
import top.potens.web.request.MemberRegisterRequest;
import top.potens.web.response.MemberAuthBaseResponse;
import top.potens.web.service.ChannelService;
import top.potens.web.service.MemberService;
import top.potens.web.service.logic.ContentCacheService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


/**
 * Created by wenshao on 2019/6/15.
 */
@RestController
@RequestMapping("/member")
@Api(description = "用户管理操作")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {

    private final MemberService memberService;
    private final ContentCacheService contentCacheService;

    private MemberAuthBaseResponse convertMemberInfo(MemberAuthInfoBO memberAuthInfoBO) {
        MemberAuthBaseResponse memberAuthBaseResponse = new MemberAuthBaseResponse();
        BeanUtils.copyProperties(memberAuthInfoBO, memberAuthBaseResponse);
        ArrayList<MemberAuthBaseResponse.MemberAuthResponse> memberAuthResponses = new ArrayList<>();
        memberAuthInfoBO.getMemberAuthList().forEach(memberAuth -> {
            MemberAuthBaseResponse.MemberAuthResponse memberAuthResponse = memberAuthBaseResponse.createMemberAuthResponse();
            BeanUtils.copyProperties(memberAuth, memberAuthResponse);
            memberAuthResponses.add(memberAuthResponse);
        });
        memberAuthBaseResponse.setMemberAuthList(memberAuthResponses);
        return memberAuthBaseResponse;
    }

    @GetMapping("/by-id")
    @ApiOperation(value = "按id获取一条记录")
    public ApiResult<MemberAuthBaseResponse> memberById(
            @ApiParam(value = "memberId", example = "1") @RequestParam(required = true) @NotNull Integer memberId
    ) {
        AppUtil.info("按id获取member信息 memberId:[{}]", memberId);
        MemberAuthInfoBO memberAuthInfoBO = memberService.queryById(memberId);
        ApiResult<MemberAuthBaseResponse> apiResult = new ApiResult<>();
        MemberAuthBaseResponse memberAuthBaseResponse = convertMemberInfo(memberAuthInfoBO);
        apiResult.setData(memberAuthBaseResponse);
        AppUtil.info("按id获取member信息 memberId:[{}] [apiResult:{}]", memberId, JSON.toJSONString(apiResult));
        return apiResult;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册接口")
    public ApiResult<MemberAuthBaseResponse> MemberRegister(@RequestBody @Valid MemberRegisterRequest request) {
        AppUtil.info("注册用户接口 request:[{}]", JSON.toJSONString(request));
        ApiResult<MemberAuthBaseResponse> apiResult = new ApiResult<>();
        // 1参数校验
        memberService.validateRegisterParams(request);
        Integer memberId = null;
        // 2 插入数据
        if (ChannelConstant.ChannelCode.SELF_TEL.equals(request.getChannelCode())) {
            memberId = memberService.insertByMobile(request);
        } else if (ChannelConstant.ChannelCode.SELF_MAIL.equals(request.getChannelCode())) {
            memberId = memberService.insertByMail(request);
        } else {
            AppUtil.error("注册用户接口 未知的注册类型 channelCode:[{}]", request.getChannelCode());
            throw new ApiException(CodeEnums.PARAM_ERROR.getCode(), CodeEnums.PARAM_ERROR.getMsg());
        }
        // 3 查询用户数据
        MemberAuthInfoBO memberAuthInfoBO = memberService.queryById(memberId);
        MemberAuthBaseResponse memberAuthBaseResponse = convertMemberInfo(memberAuthInfoBO);
        apiResult.setData(memberAuthBaseResponse);
        AppUtil.info("注册用户接口 正常返回 request:[{}] apiResult:[{}]", JSON.toJSONString(request), JSON.toJSONString(apiResult));
        return apiResult;
    }
    @GetMapping("/visitor/login")
    @ApiOperation(value = "游客用户登录注册接口")
    public ApiResult<MemberAuthBaseResponse> memberVisitorLogin(
            @ApiParam(value = "uuid", example = "1") @RequestParam(required = true) @NotNull String uuid
    ) {
        AppUtil.info("游客注册登录接口 uuid:[{}]", uuid);
        Channel channel = contentCacheService.getChannelByCode(ChannelConstant.ChannelCode.SELF_VISITOR);
        ApiResult<MemberAuthBaseResponse> apiResult = new ApiResult<>();
        MemberAuth memberAuth = memberService.existAuth(channel.getChannelId(), uuid, null);
        Integer memberId = null;
        if (memberAuth == null) {
            // 根据uuid注册游客用户
            memberId = memberService.insertByUuid(uuid);
        } else {
            memberId = memberAuth.getMemberId();
        }
        MemberAuthInfoBO memberAuthInfoBO = memberService.queryById(memberId);
        MemberAuthBaseResponse memberAuthBaseResponse = convertMemberInfo(memberAuthInfoBO);
        apiResult.setData(memberAuthBaseResponse);
        AppUtil.info("游客注册登录接口 正常返回 uuid:[{}] apiResult:[{}]", uuid, JSON.toJSONString(apiResult));
        return apiResult;
    }

}
