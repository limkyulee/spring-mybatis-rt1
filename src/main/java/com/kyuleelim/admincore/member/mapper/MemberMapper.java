package com.kyuleelim.admincore.member.mapper;

import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberLogin;
import com.kyuleelim.admincore.member.dto.MemberSave;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<Member> retrieveMemberList();
    int retrieveMemberCount();
    Member retrieveMember(MemberSave memberSave);
    int createMember(MemberSave memberSave);
    Member retrieveMemberByEmail(MemberLogin memberLogin);
}
