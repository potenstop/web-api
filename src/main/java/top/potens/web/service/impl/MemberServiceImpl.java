package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.framework.serialization.JSON;
import top.potens.web.bmo.MemberAuthInfoBO;
import top.potens.web.common.constant.MemberConstant;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.common.util.ValidateUtil;
import top.potens.web.dao.db.auto.MemberAuthMapper;
import top.potens.web.dao.db.auto.MemberMapper;
import top.potens.web.model.Member;
import top.potens.web.model.MemberAuth;
import top.potens.web.model.MemberAuthExample;
import top.potens.web.model.MemberExample;
import top.potens.web.request.MemberRegisterRequest;
import top.potens.web.service.MemberService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2019/6/16.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final MemberAuthMapper memberAuthMapper;


    @Transactional(rollbackFor = Exception.class)
    public void createMember(Member member, MemberAuth memberAuth) {
        Date now = new Date();
        member.setCreateTime(now);
        member.setUpdateTime(now);
        memberMapper.insertSelective(member);

        memberAuth.setCreateTime(now);
        memberAuth.setUpdateTime(now);
        memberAuth.setMemberId(member.getMemberId());
        memberAuthMapper.insertSelective(memberAuth);
    }


    @Override
    public MemberAuthInfoBO queryById(@NotNull Integer memberId) {
        AppUtil.info("按id查询member信息 memberId:[{}]", String.valueOf(memberId));
        MemberAuthInfoBO memberAuthInfoBO = new MemberAuthInfoBO();
        // 查询member信息
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMemberIdEqualTo(memberId);
        List<Member> members = this.memberMapper.selectByExample(memberExample);
        if (members.size() != 1) {
           throw new ApiException(CodeEnums.MEMBER_NOT_FOUND.getCode(), CodeEnums.MEMBER_NOT_FOUND.getMsg());
        }
        BeanUtils.copyProperties(members.get(0), memberAuthInfoBO);
        // 查询auth信息
        MemberAuthExample memberAuthExample = new MemberAuthExample();
        memberAuthExample.createCriteria().andMemberIdEqualTo(memberAuthInfoBO.getMemberId());
        List<MemberAuth> memberAuthList = memberAuthMapper.selectByExample(memberAuthExample);
        memberAuthInfoBO.setMemberAuthList(memberAuthList);
        AppUtil.info("按id查询member信息 成功返回 memberId:[{}] result:[{}]", String.valueOf(memberId), JSON.toJSONString(memberAuthInfoBO));
        return memberAuthInfoBO;
    }

    @Override
    public void validateRegisterParams(MemberRegisterRequest request) {
        if (MemberConstant.IdentityType.MOBILE.equals(request.getIdentityType())) {
            // 检验手机号是否合法
            Boolean isTrue = ValidateUtil.mobile(request.getIdentifier());
            if (!isTrue) {
                AppUtil.warn("注册参数校验 手机号输入格式错误 mobile:[{}]", request.getIdentifier());
                throw new ApiException(CodeEnums.MEMBER_MOBILE_INPUT_ERROR.getCode(), CodeEnums.MEMBER_MOBILE_INPUT_ERROR.getMsg());
            }
        } else if (MemberConstant.IdentityType.MAIL.equals(request.getIdentityType())) {
            // 检验邮箱地址是否合法
            Boolean isTrue = ValidateUtil.mail(request.getIdentifier());
            if (!isTrue) {
                AppUtil.warn("注册参数校验 邮箱地址输入格式错误 mobile:[{}]", request.getIdentifier());
                throw new ApiException(CodeEnums.MEMBER_MAIL_INPUT_ERROR.getCode(), CodeEnums.MEMBER_MAIL_INPUT_ERROR.getMsg());
            }
        } else {
            AppUtil.warn("注册参数校验 不支持的类型 type:[{}]", request.getIdentityType());
            throw new ApiException(CodeEnums.PARAM_ERROR.getCode(), CodeEnums.PARAM_ERROR.getMsg());
        }
    }
    @Override
    public MemberAuth existAuth(@NotBlank Integer identityType, @NotBlank String identifier, String credential) {
        MemberAuthExample memberAuthExample = new MemberAuthExample();
        memberAuthExample.createCriteria()
                .andIdentityTypeEqualTo(identityType)
                .andIdentifierEqualTo(identifier);
        List<MemberAuth> memberAuthList = memberAuthMapper.selectByExample(memberAuthExample);
        if (memberAuthList.isEmpty()) {
            AppUtil.info("查询用户auth 不存在 identityType:[{}] identifier:[{}] credential:[{}]", identityType, identifier, credential);
            return null;
        }
        if (memberAuthList.size() != 1) {
            AppUtil.error("查询用户auth 存在多个 identityType:[{}] identifier:[{}] credential:[{}] memberAuthList:[{}]", identityType, identifier, credential, JSON.toJSONString(memberAuthList));
            throw new ApiException(CodeEnums.MEMBER_EXIST_MORE.getCode(), CodeEnums.MEMBER_EXIST_MORE.getMsg());
        }
        if (credential != null) {
            MemberAuth memberAuth = memberAuthList.get(0);
            if (memberAuth.getCredential().equals(credential)) {
                return memberAuth;
            } else {
                AppUtil.info("查询用户auth credential不匹配 identityType:[{}] identifier:[{}] credential:[{}]", identityType, identifier, credential);
                return null;
            }
        } else {
            return memberAuthList.get(0);
        }
    }
    @Override
    public Integer insertByMobile(MemberRegisterRequest request) {
        // 1 检查用户是否存在
        MemberAuth existMemberAuth = this.existAuth(MemberConstant.IdentityType.MOBILE, request.getIdentifier(), null);
        if (existMemberAuth != null) {
            AppUtil.warn("添加用户 用户存在 mobile:[{}]", request.getIdentifier());
            throw new ApiException(CodeEnums.MEMBER_EXIST.getCode(), CodeEnums.MEMBER_EXIST.getMsg());
        }
        // 2 插入数据库
        Member member = new Member();
        member.setNickname(request.getNickname());
        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setIdentityType(MemberConstant.IdentityType.MOBILE);
        memberAuth.setIdentifier(request.getIdentifier());
        memberAuth.setCredential(request.getCredential());
        this.createMember(member, memberAuth);
        return member.getMemberId();
    }
    @Override
    public Integer insertByMail(MemberRegisterRequest request) {
        // 1 检查用户是否存在
        MemberAuth existMemberAuth = this.existAuth(MemberConstant.IdentityType.MAIL, request.getIdentifier(), null);
        if (existMemberAuth != null) {
            AppUtil.warn("添加用户 用户存在 mail:[{}]", request.getIdentifier());
            throw new ApiException(CodeEnums.MEMBER_EXIST.getCode(), CodeEnums.MEMBER_EXIST.getMsg());
        }
        // 2 插入数据库
        Member member = new Member();
        member.setNickname(request.getNickname());
        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setIdentityType(MemberConstant.IdentityType.MAIL);
        memberAuth.setIdentifier(request.getIdentifier());
        memberAuth.setCredential(request.getCredential());
        this.createMember(member, memberAuth);
        return member.getMemberId();
    }

    @Override
    public Integer insertByUuid(String uuid) {
        Member member = new Member();
        member.setNickname("");
        member.setAvatar("");
        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setIdentityType(MemberConstant.IdentityType.VISITOR);
        memberAuth.setIdentifier(uuid);
        memberAuth.setCredential("");
        createMember(member, memberAuth);
        return member.getMemberId();
    }

}
