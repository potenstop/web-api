package top.potens.web.bmo;

import lombok.Data;
import top.potens.web.model.User;
import top.potens.web.model.UserAuth;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserCreateBo
 * @projectName web-api
 * @date 2019/8/6 17:59
 */
public class UserSignAuthBo extends User {
    private UserAuth userAuth;

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }
}
