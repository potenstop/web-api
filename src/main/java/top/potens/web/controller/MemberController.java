package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.potens.framework.log.AppUtil;
import top.potens.framework.model.ApiResult;
import top.potens.framework.serialization.JSON;
import top.potens.web.dao.db.tool.auto.MemberMapper;
import top.potens.web.model.Member;
import top.potens.web.model.MemberExample;
import top.potens.web.response.MemberResponse;
import top.potens.web.service.MemberService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
        AppUtil.info("按id获取member信息 [memberId:{}]", memberId);
        Member member = memberService.byId(memberId);
        ApiResult<MemberResponse> apiResult = new ApiResult<>();
        MemberResponse memberResponse = new MemberResponse();
        member.setMemberId(member.getMemberId());
        apiResult.setData(memberResponse);
        AppUtil.info("按id获取member信息 [memberId:{}] [apiResult:{}]", memberId, JSON.toJSONString(apiResult));
        return apiResult;
    }


}
