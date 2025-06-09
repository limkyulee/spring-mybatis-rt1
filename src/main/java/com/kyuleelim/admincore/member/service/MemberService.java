package com.kyuleelim.admincore.member.service;

import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberList;
import com.kyuleelim.admincore.member.dto.MemberLogin;
import com.kyuleelim.admincore.member.dto.MemberSave;

public interface MemberService {
    MemberList retrieveMemberList();
    void createMember(MemberSave memberSave);
    Member login(MemberLogin memberLoginReqDto);
}
