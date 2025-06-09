package com.kyuleelim.admincore.member.service.Impl;

import com.kyuleelim.admincore.common.exception.BizException;
import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberList;
import com.kyuleelim.admincore.member.dto.MemberLogin;
import com.kyuleelim.admincore.member.dto.MemberSave;
import com.kyuleelim.admincore.member.mapper.MemberMapper;
import com.kyuleelim.admincore.member.service.MemberService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author limkyulee
 * @version 1.0, 5/4/25
 * @see 회원 관리 Service
 */
@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @Method Name retrieveMemberList
     * @Description 회원 목록 조회
     * @return 회원 목록, 총 건 수
     */
    public MemberList retrieveMemberList() {
        // 회원 목록 조회 (Dao 호출)
        List<Member> list = memberMapper.retrieveMemberList();

        // 회원 목록 셋팅
        MemberList memberList = new MemberList();
        memberList.setList(list);

        // 회원 목록 총 건 수 조회 (Dao 호출)
        int totalCount = memberMapper.retrieveMemberCount();
        memberList.setTotalCount(totalCount);

        return memberList;
    }


    /**
     * @Method Name createMember
     * @Description 회원가입 (회원 등록)
     * @param memberSave
     */
    public void createMember(MemberSave memberSave) {
        // 이메일 중복 여부 확인
        validateDuplicateEmail(memberSave);
        // 비밀번호 암호화 처리
        memberSave.setPassword(passwordEncoder.encode(memberSave.getPassword()));

        int affectedRow = memberMapper.createMember(memberSave);

        if (affectedRow < 1) {
            throw new BizException("FAIL_TO_SAVE_MEMBER");
        }
    }

    /**
     * @Method Name login
     * @Description 로그인
     * @param memberLogin
     * @Return 로그인 사용자 정보
     */
    public Member login(MemberLogin memberLogin) {
        // 로그인 사용자 정보 조회 (Dao 호출)
        Member member = memberMapper.retrieveMemberByEmail(memberLogin);

        // 비밀번호 일치 여부 확인
        if(!passwordEncoder.matches(memberLogin.getPassword(), member.getPassword())){
            throw new BizException("LOGIN_FAILED");
        }

        return member;
    }

    /**
     * @Method Name validateDuplicateEmail
     * @Description 이메일 중복 검사
     * @param memberSave
     */
    private void validateDuplicateEmail(MemberSave memberSave) {
        Member member = memberMapper.retrieveMember(memberSave);
        if(member != null){
            throw new BizException("EMAIL_ALREADY_EXISTS");
        }
    }
}
