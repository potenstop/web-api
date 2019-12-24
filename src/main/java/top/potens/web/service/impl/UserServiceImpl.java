package top.potens.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
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
import top.potens.framework.enums.LockModel;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.framework.model.PageResponse;
import top.potens.framework.model.TokenUser;
import top.potens.framework.serialization.JSON;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.DateUtil;
import top.potens.framework.util.StringUtil;
import top.potens.framework.util.TokenUtil;
import top.potens.web.bmo.UserMoreAuthBo;
import top.potens.web.bmo.UserSignAuthBo;
import top.potens.web.code.CommonCode;
import top.potens.web.code.UserCode;
import top.potens.web.common.constant.ChannelConstant;
import top.potens.web.common.constant.CommonConstant;
import top.potens.web.common.constant.LockConstant;
import top.potens.web.common.util.ValidateUtil;
import top.potens.web.config.ApolloConfiguration;
import top.potens.web.dao.db.auto.UserAuthMapper;
import top.potens.web.dao.db.auto.UserMapper;
import top.potens.web.mapper.PersonAttributeMapper;
import top.potens.web.model.*;
import top.potens.web.model.ldap.Person;
import top.potens.web.request.UserListRequest;
import top.potens.web.request.UserOutRequest;
import top.potens.web.request.UserRegisterRequest;
import top.potens.web.response.UserListItemResponse;
import top.potens.web.service.ChannelService;
import top.potens.web.service.UserService;
import top.potens.web.service.logic.CacheServiceLogic;
import top.potens.web.service.logic.UserServiceLogic;
import top.potens.web.service.noe4j.Neo4jService;
import top.potens.wechat.response.WechatUserBasicInfoResponse;
import top.potens.wechat.service.WechatUserService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by wenshao on 2019/6/16.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends AbstractSimpleTableCommonServiceImpl<User> implements UserService {
    private final UserMapper userMapper;
    private final UserAuthMapper userAuthMapper;
    private final Neo4jService neo4JService;
    private final CacheServiceLogic cacheServiceLogic;
    private final UserServiceLogic userServiceLogic;
    private final LdapTemplate ldapTemplate;
    private final RestTemplate restTemplate;
    private final ApolloConfiguration apolloConfiguration;
    private final WechatUserService wechatUserService;
    @Override
    protected User mapperByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    protected User mapperBySecondPrimaryKey(Integer id) {
        return null;
    }

    @Override
    protected Boolean isDelete(User user) {
        return false;
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
                .andChannelIdEqualTo(channelId)
                .andIdentifierEqualTo(identifier);
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
        Channel channel = cacheServiceLogic.getChannelByCode(ChannelConstant.ChannelCode.SELF_TEL);
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
        Channel channel = cacheServiceLogic.getChannelByCode(ChannelConstant.ChannelCode.SELF_MAIL);

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
        Channel channel = cacheServiceLogic.getChannelByCode(ChannelConstant.ChannelCode.SELF_VISITOR);
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
        Channel channel = cacheServiceLogic.getChannelByCode(ChannelConstant.ChannelCode.SELF_LDAP);
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
    @Lock(lockModel = LockModel.FAIR, keys = LockConstant.LDAP_LOGIN + "#{#username}", attemptTimeout = 10, lockWatchTimeout = 100)
    public String ldapLogin(Channel channel, String username, String password) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(apolloConfiguration.getLdapIdentifier(), username));
        boolean authenticate = ldapTemplate.authenticate("", filter.encode(), password);
        if (authenticate) {
            List<Person> personList = ldapTemplate.search("", filter.encode(), new PersonAttributeMapper());
            if (CollectionUtils.isEmpty(personList) || personList.size() != 1) {
                AppLogger.warn("ldap登录 用户不存在或存在多个 username:[{}] password:[{}] personList:[{}]", username, password, JSON.toJSONString(personList));
                throw new ApiException(UserCode.USERNAME_OR_PASSWORD_ERROR);
            }
            Person person = personList.get(0);
            UserAuth userAuth = existAuth(channel.getChannelId(), person.getUidNumber(), null);
            TokenUser tokenUser = new TokenUser();
            tokenUser.setUsername(username);
            if (userAuth == null) {
                // 创建用户
                Integer userId = insertByLdap(person.getUidNumber(), username);
                tokenUser.setUserId(userId);
            } else {
                tokenUser.setUserId(userAuth.getUserId());
            }
            return TokenUtil.updateToken(tokenUser);
        } else {
            AppLogger.warn("ldap登录 用户名或密码错误 username:[{}] password:[{}]", username, password);
            throw new ApiException(UserCode.USERNAME_OR_PASSWORD_ERROR);
        }
    }

    @Override
    public PageResponse<UserListItemResponse> userList(UserListRequest request) {
        PageResponse<UserListItemResponse> response = new PageResponse<>();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (request.getUserId() != null) {
            criteria.andUserIdEqualTo(request.getUserId());
        }
        if (StringUtil.isNotBlank(request.getCreateTime())) {
            String[] split = request.getCreateTime().split(",");
            if (split.length == 1) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.getLocalDate(split[0]));
            } else if (split.length == 2) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.getLocalDate(split[0]));
                criteria.andCreateTimeLessThan(DateUtil.getDateByLocalDate(DateUtil.parseLocalDate(split[1]).plusDays(1)));
            }
        }
        if (StringUtil.isNotBlank(request.getUpdateTime())) {
            String[] split = request.getUpdateTime().split(",");
            if (split.length == 1) {
                criteria.andUpdateTimeGreaterThanOrEqualTo(DateUtil.getLocalDate(split[0]));
            } else if (split.length == 2) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.getLocalDate(split[0]));
                criteria.andUpdateTimeLessThan(DateUtil.getDateByLocalDate(DateUtil.parseLocalDate(split[1]).plusDays(1)));
            }
        }
        if (StringUtil.isNotBlank(request.getNickname())) {
            criteria.andNicknameLike("%" + request.getNickname() + "%");
        }
        if (StringUtil.isNotBlank(request.getOrderBy())) {
            userExample.setOrderByClause(request.getOrderBy());
        } else {
            userExample.setOrderByClause("user_id desc");
        }
        List<User> users = userMapper.selectByExample(userExample);
        PageSerializable<User> userPageSerializable = PageSerializable.of(users);
        response.setTotal(userPageSerializable.getTotal());
        List<User> list = userPageSerializable.getList();
        BeanCopierUtil.convert(list, response.getList(), UserListItemResponse.class);
        return response;
    }

    @Override
    @Lock(lockModel = LockModel.FAIR, keys = LockConstant.WXMP_LOGIN + "#{#wxmpDevName}" + ":" + "#{#openId}", attemptTimeout = 10, lockWatchTimeout = 100)
    public UserMoreAuthBo wxmpLogin(String wxmpDevName, String openId) {
        Channel channel = cacheServiceLogic.getChannelByCode(wxmpDevName);
        // 判断用户是否存在
        UserAuth userAuth = existAuth(channel.getChannelId(), openId, null);
        UserMoreAuthBo userMoreAuthBo = new UserMoreAuthBo();
        List<UserAuth> userAuthList = new ArrayList<>();
        if (userAuth == null) {
            WechatUserBasicInfoResponse userInfo = wechatUserService.getUserInfo(openId);
            // 用户不存在 需要创建用户
            User user = new User();
            user.setNickname(userInfo.getNickname());
            user.setAvatar(userInfo.getHeadImgUrl());
            UserAuth userAuthInsert = new UserAuth();
            userAuthInsert.setChannelId(channel.getChannelId());
            userAuthInsert.setIdentifier(openId);
            userAuthInsert.setCredential("");
            userServiceLogic.create(user, userAuthInsert);
            BeanCopierUtil.convert(user, userMoreAuthBo);

            userAuthList.add(userAuthInsert);
            userMoreAuthBo.setUserAuthList(userAuthList);
        } else {
            User user = byPrimaryKey(userAuth.getUserId());
            BeanCopierUtil.convert(user, userMoreAuthBo);
            userAuthList.add(userAuth);
        }
        return userMoreAuthBo;
    }
}
