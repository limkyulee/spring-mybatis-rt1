package com.kyuleelim.admincore.member.service;

import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberListResDto;
import com.kyuleelim.admincore.member.dto.MemberLoginReqDto;
import com.kyuleelim.admincore.member.dto.MemberSaveReqDto;

public interface MemberService {
    Member join(MemberSaveReqDto memberSaveReqDto);
    Member login(MemberLoginReqDto memberLoginReqDto);
    MemberListResDto findAll();
}
