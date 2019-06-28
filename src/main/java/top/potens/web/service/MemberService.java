package top.potens.web.service;

import top.potens.web.bmo.MemberAuthInfoBO;
import top.potens.web.model.Member;
import top.potens.web.model.MemberAuth;
import top.potens.web.request.MemberRegisterRequest;

import javax.validation.constraints.NotBlank;

/**
 * Created by wenshao on 2019/6/16.
 */
public interface MemberService {
    /**
     * 按id查询用户的信息
     * @param memberId  memberId
     * @return          用户信息
     */
    MemberAuthInfoBO queryById(Integer memberId);

    /**
     * 检查注册用户接口参数
     * @param request   参数
     */
    void validateRegisterParams(MemberRegisterRequest request);


    /**
     * 查询对应的auth记录 只能有一条
     * @param identityType  类型
     * @param identifier    标识
     * @param credential    密码
     * @return              MemberAuth
     */
    MemberAuth existAuth(@NotBlank Integer identityType, @NotBlank String identifier, String credential);

    /**
     * 按手机号插入用户
     * @param request   参数
     */
    Integer insertByMobile(MemberRegisterRequest request);
    /**
     * 按邮箱插入用户
     * @param request   参数
     */
    Integer insertByMail(MemberRegisterRequest request);

    /**
     * 插入游客用户
     * @param uuid  uuid
     */
    Integer insertByUuid(String uuid);
}
