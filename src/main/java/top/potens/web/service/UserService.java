package top.potens.web.service;

import top.potens.web.bmo.UserMoreAuthBo;
import top.potens.web.bmo.UserSignAuthBo;
import top.potens.web.model.Channel;
import top.potens.web.model.User;
import top.potens.web.model.UserAuth;
import top.potens.web.request.UserOutRequest;
import top.potens.web.request.UserRegisterRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wenshao on 2019/6/16.
 */
public interface UserService {

    /**
     * 按id查询用户的信息
     * @param userId  userId
     * @return          用户信息
     */
    User queryById(@NotNull Integer userId);
    /**
     * 按id查询用户的详细信息
     * @param userId  userId
     * @return          用户信息
     */
    UserMoreAuthBo queryDetailById(Integer userId);

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

    /**
     * 插入ldap用户
     * @param uid  uid
     * @param cn   cn
     */
    Integer insertByLdap(String uid, String cn);

    /**
    *
    * 方法功能描述: 插入外部用户
    *
    * @author yanshaowen
    * @date 2019/8/6 18:41
    * @param requests   requests
    * @param channelId  channelId
    * @return
    * @throws
    */
    List<UserSignAuthBo> insertByOutList(List<UserOutRequest> requests, Integer channelId);

    /**
     *
     * 方法功能描述: ldap用户登录
     *
     * @author yanshaowen
     * @date 2019/8/6 18:41
     * @param username   username
     * @param password   password
     * @return
     * @throws
     */
    String ldapLogin(Channel channel, String username, String password);
}
