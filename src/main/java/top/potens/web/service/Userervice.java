package top.potens.web.service;

import top.potens.web.bmo.UserAuthInfoBO;
import top.potens.web.model.UserAuth;
import top.potens.web.request.UserRegisterRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by wenshao on 2019/6/16.
 */
public interface Userervice {
    /**
     * 按id查询用户的信息
     * @param userId  userId
     * @return          用户信息
     */
    UserAuthInfoBO queryById(Integer userId);

    /**
     * 检查注册用户接口参数
     * @param request   参数
     */
    void validateRegisterParams(UserRegisterRequest request);


    /**
     * 查询对应的auth记录 只能有一条
     * @param channelId   渠道码
     * @param identifier    标识
     * @param credential    密码
     * @return              UserAuth
     */
    UserAuth existAuth(@NotNull Integer channelId, @NotBlank String identifier, String credential);

    /**
     * 按手机号插入用户
     * @param request   参数
     */
    Integer insertByMobile(UserRegisterRequest request);
    /**
     * 按邮箱插入用户
     * @param request   参数
     */
    Integer insertByMail(UserRegisterRequest request);

    /**
     * 插入游客用户
     * @param uuid  uuid
     */
    Integer insertByUuid(String uuid);
}
