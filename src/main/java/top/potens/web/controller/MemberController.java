package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.potens.framework.log.AppUtil;
import top.potens.framework.model.ApiResult;
import top.potens.framework.serialization.JSON;
import top.potens.web.common.constant.MemberConstant;
import top.potens.web.model.Member;
import top.potens.web.request.MemberRegisterRequest;
import top.potens.web.response.MemberResponse;
import top.potens.web.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Created by wenshao on 2019/6/15.
 */
@RestController
@RequestMapping("/member")
@Api(description = "用户管理操作")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/by-id")
    @Validated
    public ApiResult<MemberResponse> byId(
            @ApiParam(value = "memberId", example = "1") @RequestParam(required = true) @NotNull Integer memberId
    ) {
        AppUtil.info("按id获取member信息 memberId:[{}]", memberId);
        Member member = memberService.queryById(memberId);
        ApiResult<MemberResponse> apiResult = new ApiResult<>();
        MemberResponse memberResponse = new MemberResponse();
        member.setMemberId(member.getMemberId());
        apiResult.setData(memberResponse);
        AppUtil.info("按id获取member信息 memberId:[{}] [apiResult:{}]", memberId, JSON.toJSONString(apiResult));
        return apiResult;
    }
    @PostMapping("/register")
    @Validated
    public ApiResult<MemberResponse> register(@RequestBody @Valid MemberRegisterRequest request) {
        AppUtil.info("注册用户接口 request:[{}]", JSON.toJSONString(request));
        ApiResult<MemberResponse> apiResult = new ApiResult<>();
        // 1参数校验
        memberService.validateRegisterParams(request);
        if (MemberConstant.IdentityType.MOBILE.equals(request.getIdentityType())) {
            memberService.insertByMobile(request);
        } else if (MemberConstant.IdentityType.MAIL.equals(request.getIdentityType())) {
            // memberService.ins(request);
        }
        AppUtil.info("注册用户接口 正常返回 request:{}] apiResult:[{}]", JSON.toJSONString(request), JSON.toJSONString(apiResult));
        return apiResult;
    }


}
