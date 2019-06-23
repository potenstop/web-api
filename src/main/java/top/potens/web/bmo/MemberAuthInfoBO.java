package top.potens.web.bmo;

import top.potens.web.model.Member;
import top.potens.web.model.MemberAuth;

import java.util.List;

/**
 * Created by wenshao on 2019/6/23.
 */
public class MemberAuthInfoBO extends Member {
    private List<MemberAuth> memberAuthList;

    public List<MemberAuth> getMemberAuthList() {
        return memberAuthList;
    }

    public void setMemberAuthList(List<MemberAuth> memberAuthList) {
        this.memberAuthList = memberAuthList;
    }
}
