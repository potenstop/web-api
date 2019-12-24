package top.potens.web.bmo;

import lombok.Data;
import top.potens.web.model.User;
import top.potens.web.model.UserAuth;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserMoreAuthBo
 * @projectName web-api
 * @date 2019/8/6 17:59
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
