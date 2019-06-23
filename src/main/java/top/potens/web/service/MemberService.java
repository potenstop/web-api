package top.potens.web.service;

import top.potens.web.bmo.MemberAuthInfoBO;
import top.potens.web.model.Member;
import top.potens.web.request.MemberRegisterRequest;

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
     * 按手机号插入用户
     * @param request   参数
     */
    void insertByMobile(MemberRegisterRequest request);
}
