package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.potens.web.bmo.UserMoreAuthBo;
import top.potens.web.bmo.UserSignAuthBo;
import top.potens.web.dao.db.auto.UserAuthMapper;
import top.potens.web.dao.db.auto.UserMapper;
import top.potens.web.model.User;
import top.potens.web.model.UserAuth;
import top.potens.web.model.UserAuthExample;
import top.potens.web.model.UserExample;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserServiceLogic
 * @projectName web-api
 * @date 2019/8/6 17:39
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceLogic {
    private final UserMapper userMapper;
    private final UserAuthMapper userAuthMapper;

    @Transactional(rollbackFor = Exception.class)
    public void create(User user, UserAuth userAuth) {
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userMapper.insertSelective(user);

        userAuth.setCreateTime(now);
        userAuth.setUpdateTime(now);
        userAuth.setUserId(user.getUserId());
        userAuthMapper.insertSelective(userAuth);
    }

    @Transactional(rollbackFor = Exception.class)
    public void bulkCreateOrUpdate(List<UserSignAuthBo> userSignAuthBos) {
        Date now = new Date();
        userSignAuthBos.forEach(userSignAuthBo -> {
            // 判断是否存在
            UserAuth userAuth = userSignAuthBo.getUserAuth();
            UserAuthExample userAuthExample = new UserAuthExample();
            userAuthExample.createCriteria()
                    .andChannelIdEqualTo(userAuth.getChannelId())
                    .andIdentifierEqualTo(userAuth.getIdentifier());
            List<UserAuth> userAuthList = userAuthMapper.selectByExample(userAuthExample);
            if (CollectionUtils.isEmpty(userAuthList)) {
                // 新建
                userSignAuthBo.setCreateTime(now);
                userSignAuthBo.setUpdateTime(now);
                userMapper.insertSelective(userSignAuthBo);

                userAuth.setCreateTime(now);
                userAuth.setUpdateTime(now);
                userAuth.setUserId(userSignAuthBo.getUserId());
                userAuthMapper.insertSelective(userAuth);
            } else {
                // 更新 auth
                Integer userId = userAuthList.get(0).getUserId();
                Integer userAuthId = userAuthList.get(0).getUserAuthId();
                UserAuth updateAuth = new UserAuth();
                updateAuth.setUpdateTime(now);
                updateAuth.setCredential(userAuth.getCredential());
                UserAuthExample updateAuthExample = new UserAuthExample();
                updateAuthExample.createCriteria().andUserAuthIdEqualTo(userAuthId);
                userAuthMapper.updateByExampleSelective(updateAuth, updateAuthExample);
                // 更新 user
                User updateUser = new User();
                updateUser.setUpdateTime(now);
                updateUser.setNickname(userSignAuthBo.getNickname());
                updateUser.setAvatar(userSignAuthBo.getAvatar());
                UserExample updateUserExample = new UserExample();
                updateUserExample.createCriteria().andUserIdEqualTo(userId);
                userMapper.updateByExampleSelective(updateUser, updateUserExample);
                // set id
                userSignAuthBo.setUserId(userId);
                userSignAuthBo.getUserAuth().setUserId(userId);
                userSignAuthBo.getUserAuth().setUserAuthId(userAuthId);
            }
        });
    }
}
