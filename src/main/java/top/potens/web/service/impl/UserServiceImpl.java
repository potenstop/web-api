package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.potens.framework.annotation.Lock;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.web.bmo.UserMoreAuthBo;
import top.potens.web.bmo.UserSignAuthBo;
import top.potens.web.code.CommonCode;
import top.potens.web.code.UserCode;
import top.potens.web.common.constant.ChannelConstant;
import top.potens.web.common.constant.CommonConstant;
import top.potens.web.common.util.ValidateUtil;
import top.potens.web.config.ApolloConfiguration;
import top.potens.web.dao.db.auto.UserAuthMapper;
import top.potens.web.dao.db.auto.UserMapper;
import top.potens.web.mapper.PersonAttributeMapper;
import top.potens.web.model.*;
import top.potens.web.model.ldap.Person;
import top.potens.web.request.UserOutRequest;
import top.potens.web.request.UserRegisterRequest;
import top.potens.web.service.UserService;
import top.potens.web.service.logic.ContentCacheService;
import top.potens.web.service.logic.UserServiceLogic;
import top.potens.web.service.noe4j.Neo4jService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static top.potens.framework.enums.LockModel.FAIR;
import static top.potens.web.common.constant.LockConstant.LDAP_LOGIN;

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
    private final LdapTemplate ldapTemplate;
    private final RestTemplate restTemplate;
    private final ApolloConfiguration apolloConfiguration;

    @Override
    public User queryById(@NotNull Integer userId) {
        // 查询user信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 1) {
            throw new ApiException(UserCode.USER_NOT_FOUND);
        }
        if (CommonConstant.IsDelete.YES.equals(users.get(0).getIsDelete())) {
            AppLogger.warn("查询user 用户已经被删除 userId:[{}]", userId);
            throw new ApiException(UserCode.USER_NOT_FOUND);
        }
        return users.get(0);
    }

    @Override
    public UserMoreAuthBo queryDetailById(@NotNull Integer userId) {
        AppLogger.info("按id查询user详细信息 userId:[{}]", String.valueOf(userId));
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(CommonConstant.IsDelete.NO);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 1) {
            throw new ApiException(UserCode.USER_NOT_FOUND);
        }
        if (CommonConstant.IsDelete.YES.equals(users.get(0).getIsDelete())) {
            AppLogger.warn("查询user 用户已经被删除 userId:[{}]", userId);
            throw new ApiException(UserCode.USER_NOT_FOUND);
        }
        User user = users.get(0);
        UserMoreAuthBo userMoreAuthBo = new UserMoreAuthBo();
        BeanUtils.copyProperties(user, userMoreAuthBo);
        // 查询auth信息
        UserAuthExample userAuthExample = new UserAuthExample();
        userAuthExample.createCriteria().andUserIdEqualTo(userMoreAuthBo.getUserId());
        List<UserAuth> userAuthList = userAuthMapper.selectByExample(userAuthExample);
        userMoreAuthBo.setUserAuthList(userAuthList);
        AppLogger.info("按id查询user详细信息 成功返回 userId:[{}] result:[{}]", String.valueOf(userId), JSON.toJSONString(userMoreAuthBo));
        return userMoreAuthBo;
    }

    @Override
    public void validateRegisterParams(UserRegisterRequest request) {
        if (ChannelConstant.ChannelCode.SELF_TEL.equals(request.getChannelCode())) {
            // 检验手机号是否合法
            Boolean isTrue = ValidateUtil.mobile(request.getIdentifier());
            if (!isTrue) {
                AppLogger.warn("注册参数校验 手机号输入格式错误 mobile:[{}]", request.getIdentifier());
                throw new ApiException(UserCode.USER_MOBILE_INPUT_ERROR);
            }
        } else if (ChannelConstant.ChannelCode.SELF_MAIL.equals(request.getChannelCode())) {
            // 检验邮箱地址是否合法
            Boolean isTrue = ValidateUtil.mail(request.getIdentifier());
            if (!isTrue) {
                AppLogger.warn("注册参数校验 邮箱地址输入格式错误 mobile:[{}]", request.getIdentifier());
                throw new ApiException(UserCode.USER_MAIL_INPUT_ERROR);
            }
        } else {
            AppLogger.warn("注册参数校验 不支持的类型 channelCode:[{}]", request.getChannelCode());
            throw new ApiException(CommonCode.PARAM_ERROR);
        }
    }
    @Override
    public UserAuth existAuth(@NotNull Integer channelId, @NotBlank String identifier, String credential) {

        UserAuthExample userAuthExample = new UserAuthExample();
        userAuthExample.createCriteria()
                .andChannelIdEqualTo(channelId);
        List<UserAuth> userAuthList = userAuthMapper.selectByExample(userAuthExample);
        
        if (userAuthList.isEmpty()) {
            AppLogger.info("查询用户auth 不存在 identityType:[{}] channelId:[{}] credential:[{}]", channelId, identifier, credential);
            return null;
        }

        // 批量查user 判断isDelete
        List<Integer> userIds = userAuthList.stream().map(UserAuth::getUserId).collect(Collectors.toList());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIn(userIds).andIsDeleteEqualTo(CommonConstant.IsDelete.NO);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Integer, List<User>> userGroupBy = users.stream().collect(Collectors.groupingBy(User::getUserId));

        ArrayList<UserAuth> userAuthExclude = new ArrayList<>();

        userAuthList.forEach(userAuth -> {
           if (userGroupBy.containsKey(userAuth.getUserId())) {
               userAuthExclude.add(userAuth);
           }
        });
        
        if (userAuthExclude.size() != 1) {
            AppLogger.error("查询用户auth 存在多个 identityType:[{}] channelId:[{}] credential:[{}] UserAuthList:[{}]", channelId, identifier, credential, JSON.toJSONString(userAuthList));
            throw new ApiException(UserCode.USER_EXIST_MORE);
        }
        if (credential != null) {
            UserAuth userAuth = userAuthExclude.get(0);
            if (userAuth.getCredential().equals(credential)) {
                return userAuth;
            } else {
                AppLogger.info("查询用户auth credential不匹配 identityType:[{}] channelId:[{}] credential:[{}]", channelId, identifier, credential);
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
            AppLogger.warn("添加用户 用户存在 mobile:[{}]", request.getIdentifier());
            throw new ApiException(UserCode.USER_EXIST_MORE);
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
            AppLogger.warn("添加用户 用户存在 mail:[{}]", request.getIdentifier());
            throw new ApiException(UserCode.USER_EXIST_MORE);
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
    public Integer insertByLdap(String uid, String cn) {
        Channel channel = contentCacheService.getChannelByCode(ChannelConstant.ChannelCode.SELF_LDAP);
        User user = new User();
        user.setNickname(cn);
        user.setAvatar("");
        UserAuth userAuth = new UserAuth();
        userAuth.setChannelId(channel.getChannelId());
        userAuth.setIdentifier(uid);
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

    @Override
    @Lock(lockModel = FAIR, keys = "#{username}", attemptTimeout = 10, lockWatchTimeout = 100)
    public boolean ldapLogin(Channel channel, String username, String password) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(apolloConfiguration.getLdapIdentifier(), username));
        filter.and(new EqualsFilter("userPassword", password));
        List<Person> search = ldapTemplate.search("", filter.encode(), new PersonAttributeMapper());
        if (CollectionUtils.isNotEmpty(search) && search.size() == 1) {
            UserAuth userAuth = existAuth(channel.getChannelId(), username, null);
            Person person = search.get(0);
            if (userAuth == null) {
                // 创建用户
                insertByLdap(person.getUid(), person.getCn());
            }
            return true;
        } else {
            AppLogger.warn("ldap登录 用户名或密码错误 username:[{}] password:[{}]", username, password);
            throw new ApiException(UserCode.USERNAME_OR_PASSWORD_ERROR);
        }
    }
}
