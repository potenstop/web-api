package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.framework.serialization.JSON;
import top.potens.web.bmo.UserMoreAuthBo;
import top.potens.web.bmo.UserSignAuthBo;
import top.potens.web.common.constant.ChannelConstant;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.common.util.ValidateUtil;
import top.potens.web.dao.db.auto.UserAuthMapper;
import top.potens.web.dao.db.auto.UserMapper;
import top.potens.web.model.*;
import top.potens.web.request.UserOutRequest;
import top.potens.web.request.UserRegisterRequest;
import top.potens.web.service.UserService;
import top.potens.web.service.logic.ContentCacheService;
import top.potens.web.service.logic.UserServiceLogic;
import top.potens.web.service.noe4j.Neo4jService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by wenshao on 2019/6/16.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserAuthMapper userAuthMapper;
    private final Neo4jService neo4JService;
    private final ContentCacheService contentCacheService;
    private final UserServiceLogic userServiceLogic;

    @Override
    public UserMoreAuthBo queryById(@NotNull Integer userId) {
        AppUtil.info("按id查询user信息 userId:[{}]", String.valueOf(userId));
        UserMoreAuthBo userMoreAuthBo = new UserMoreAuthBo();
        // 查询user信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 1) {
           throw new ApiException(CodeEnums.USER_NOT_FOUND.getCode(), CodeEnums.USER_NOT_FOUND.getMsg());
        }
        BeanUtils.copyProperties(users.get(0), userMoreAuthBo);
        // 查询auth信息
        UserAuthExample userAuthExample = new UserAuthExample();
        userAuthExample.createCriteria().andUserIdEqualTo(userMoreAuthBo.getUserId());
        List<UserAuth> userAuthList = userAuthMapper.selectByExample(userAuthExample);
        userMoreAuthBo.setUserAuthList(userAuthList);
        AppUtil.info("按id查询user信息 成功返回 userId:[{}] result:[{}]", String.valueOf(userId), JSON.toJSONString(userMoreAuthBo));
        return userMoreAuthBo;
    }

    @Override
    public void validateRegisterParams(UserRegisterRequest request) {
        if (ChannelConstant.ChannelCode.SELF_TEL.equals(request.getChannelCode())) {
            // 检验手机号是否合法
            Boolean isTrue = ValidateUtil.mobile(request.getIdentifier());
            if (!isTrue) {
                AppUtil.warn("注册参数校验 手机号输入格式错误 mobile:[{}]", request.getIdentifier());
                throw new ApiException(CodeEnums.USER_MOBILE_INPUT_ERROR.getCode(), CodeEnums.USER_MOBILE_INPUT_ERROR.getMsg());
            }
        } else if (ChannelConstant.ChannelCode.SELF_MAIL.equals(request.getChannelCode())) {
            // 检验邮箱地址是否合法
            Boolean isTrue = ValidateUtil.mail(request.getIdentifier());
            if (!isTrue) {
                AppUtil.warn("注册参数校验 邮箱地址输入格式错误 mobile:[{}]", request.getIdentifier());
                throw new ApiException(CodeEnums.USER_MAIL_INPUT_ERROR.getCode(), CodeEnums.USER_MAIL_INPUT_ERROR.getMsg());
            }
        } else {
            AppUtil.warn("注册参数校验 不支持的类型 channelCode:[{}]", request.getChannelCode());
            throw new ApiException(CodeEnums.PARAM_ERROR.getCode(), CodeEnums.PARAM_ERROR.getMsg());
        }
    }
    @Override
    public UserAuth existAuth(@NotNull Integer channelId, @NotBlank String identifier, String credential) {

        UserAuthExample userAuthExample = new UserAuthExample();
        userAuthExample.createCriteria()
                .andChannelIdEqualTo(channelId)
                .andIdentifierEqualTo(identifier);
        List<UserAuth> userAuthList = userAuthMapper.selectByExample(userAuthExample);
        if (userAuthList.isEmpty()) {
            AppUtil.info("查询用户auth 不存在 identityType:[{}] channelId:[{}] credential:[{}]", channelId, identifier, credential);
            return null;
        }
        if (userAuthList.size() != 1) {
            AppUtil.error("查询用户auth 存在多个 identityType:[{}] channelId:[{}] credential:[{}] UserAuthList:[{}]", channelId, identifier, credential, JSON.toJSONString(userAuthList));
            throw new ApiException(CodeEnums.USER_EXIST_MORE.getCode(), CodeEnums.USER_EXIST_MORE.getMsg());
        }
        if (credential != null) {
            UserAuth userAuth = userAuthList.get(0);
            if (userAuth.getCredential().equals(credential)) {
                return userAuth;
            } else {
                AppUtil.info("查询用户auth credential不匹配 identityType:[{}] channelId:[{}] credential:[{}]", channelId, identifier, credential);
                return null;
            }
        } else {
            return userAuthList.get(0);
        }
    }
    @Override
    public Integer insertByMobile(UserRegisterRequest request) {
        Channel channel = contentCacheService.getChannelByCode(ChannelConstant.ChannelCode.SELF_TEL);
        // 1 检查用户是否存在
        UserAuth existUserAuth = this.existAuth(channel.getChannelId(), request.getIdentifier(), null);
        if (existUserAuth != null) {
            AppUtil.warn("添加用户 用户存在 mobile:[{}]", request.getIdentifier());
            throw new ApiException(CodeEnums.USER_EXIST.getCode(), CodeEnums.USER_EXIST.getMsg());
        }
        // 2 插入数据库
        User user = new User();
        user.setNickname(request.getNickname());
        UserAuth userAuth = new UserAuth();
        userAuth.setChannelId(channel.getChannelId());
        userAuth.setIdentifier(request.getIdentifier());
        userAuth.setCredential(request.getCredential());
        userServiceLogic.create(user, userAuth);
        return user.getUserId();
    }
    @Override
    public Integer insertByMail(UserRegisterRequest request) {
        Channel channel = contentCacheService.getChannelByCode(ChannelConstant.ChannelCode.SELF_MAIL);

        // 1 检查用户是否存在
        UserAuth existUserAuth = this.existAuth(channel.getChannelId(), request.getIdentifier(), null);
        if (existUserAuth != null) {
            AppUtil.warn("添加用户 用户存在 mail:[{}]", request.getIdentifier());
            throw new ApiException(CodeEnums.USER_EXIST.getCode(), CodeEnums.USER_EXIST.getMsg());
        }
        // 2 插入数据库
        User user = new User();
        user.setNickname(request.getNickname());
        UserAuth userAuth = new UserAuth();
        userAuth.setChannelId(channel.getChannelId());
        userAuth.setIdentifier(request.getIdentifier());
        userAuth.setCredential(request.getCredential());
        userServiceLogic.create(user, userAuth);
        return user.getUserId();
    }

    @Override
    public Integer insertByUuid(String uuid) {
        Channel channel = contentCacheService.getChannelByCode(ChannelConstant.ChannelCode.SELF_VISITOR);
        User user = new User();
        user.setNickname("");
        user.setAvatar("");
        UserAuth userAuth = new UserAuth();
        userAuth.setChannelId(channel.getChannelId());
        userAuth.setIdentifier(uuid);
        userAuth.setCredential("");
        userServiceLogic.create(user, userAuth);
        return user.getUserId();
    }
    @Override
    public List<UserSignAuthBo> insertByOutList(List<UserOutRequest> requests, Integer channelId) {
        List<UserSignAuthBo> userSignAuthBos = new ArrayList<>();
        requests.forEach(request -> {
            UserSignAuthBo userSignAuthBo = new UserSignAuthBo();
            userSignAuthBo.setNickname(request.getUserName());
            userSignAuthBo.setAvatar(request.getAvatar());
            UserAuth userAuth = new UserAuth();
            userAuth.setIdentifier(request.getSourceUserId());
            userAuth.setChannelId(channelId);
            userSignAuthBo.setUserAuth(userAuth);
            userSignAuthBos.add(userSignAuthBo);
        });
        userServiceLogic.bulkCreateOrUpdate(userSignAuthBos);
        return userSignAuthBos;
    }
}
