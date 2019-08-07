package top.potens.web.bmo;

import lombok.Data;
import top.potens.web.model.User;
import top.potens.web.model.UserAuth;

import java.util.List;

/**
 * Created by wenshao on 2019/6/23.
 */
public class UserMoreAuthBo extends User {
    private List<UserAuth> userAuthList;

    public List<UserAuth> getUserAuthList() {
        return userAuthList;
    }

    public void setUserAuthList(List<UserAuth> userAuthList) {
        this.userAuthList = userAuthList;
    }
}
