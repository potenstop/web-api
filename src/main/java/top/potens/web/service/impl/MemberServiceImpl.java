package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.dao.db.tool.auto.MemberMapper;
import top.potens.web.model.Member;
import top.potens.web.model.MemberExample;
import top.potens.web.response.MemberResponse;
import top.potens.web.service.MemberService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshao on 2019/6/16.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Override
    public Member byId(Integer memberId) {
        MemberExample memberExample = new MemberExample();

        memberExample.createCriteria().andMemberIdEqualTo(memberId);
        List<Member> members = this.memberMapper.selectByExample(memberExample);
        MemberResponse memberResponse = new MemberResponse();
        if (members.size() != 1) {
           throw new ApiException(CodeEnums.MEMBER_NOT_FOUND.getCode(), CodeEnums.MEMBER_NOT_FOUND.getMsg());
        }
        return members.get(0);

    }
}
