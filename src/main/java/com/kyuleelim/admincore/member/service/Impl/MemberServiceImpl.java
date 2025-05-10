package com.kyuleelim.admincore.member.service.Impl;

import com.kyuleelim.admincore.common.exception.BizException;
import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberListResDto;
import com.kyuleelim.admincore.member.dto.MemberLoginReqDto;
import com.kyuleelim.admincore.member.dto.MemberSaveReqDto;
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
     * @Method Name join
     * @Description 회원가입
     * @param memberSaveReqDto
     * @return 회원가입 요청 정보
     */
    public Member join(MemberSaveReqDto memberSaveReqDto) {
        validateDuplicateEmail(memberSaveReqDto.getEmail());

        Member member = createMember(memberSaveReqDto);
        memberMapper.save(member);

        return member;
    }

    /**
     * @Method Name login
     * @Description 로그인
     * @param memberLoginReqDto
     * @return 로그인 요청 정보
     */
    public Member login(MemberLoginReqDto memberLoginReqDto) {
        Member member = memberMapper.findByEmail(memberLoginReqDto.getEmail())
            .orElseThrow(() -> new BizException("user_not_found"));
        log.info("member {}", member);
        log.info("로그인 시도 email: {}", memberLoginReqDto.getEmail());
        log.info("role: {}", member.getRoleNm());
        log.info("입력된 비밀번호(plain): {}", memberLoginReqDto.getPassword());
        log.info("DB에서 읽은 비밀번호(encoded): {}", member.getPassword());

        // matches | 자동 암호화
        if(!passwordEncoder.matches(memberLoginReqDto.getPassword(), member.getPassword())){
            throw  new BizException("wrong_login_info");
        }

        return member;
    }

    /**
     * @Method Name findAll
     * @Description 회원 전체 목록 조회
     * @return 회원 전체 목록
     */
    public MemberListResDto findAll() {
        List<Member> members = memberMapper.findAll();

        MemberListResDto response = new MemberListResDto();
        response.setList(members);
        response.setTotalCount(members.size());

        return response;
    }

    /**
     * @Method Name validateDuplicateEmail
     * @Description 이메일 중복 검사
     * @param email
     */
    private void validateDuplicateEmail(String email) {
        memberMapper.findByEmail(email)
            .ifPresent(m -> {
                throw new BizException("duplicate_email");
            });
    }

    /**
     * @Method Name createMember
     * @Description 회원가입을 위한 멤버 생성
     * @param memberSaveReqDto
     * @return member
     */
    private Member createMember(MemberSaveReqDto memberSaveReqDto) {
        Member member = new Member();
        member.setUserNm(memberSaveReqDto.getUserNm());
        member.setEmail(memberSaveReqDto.getEmail());
        member.setPassword(passwordEncoder.encode(memberSaveReqDto.getPassword()));
        member.setRoleNm(memberSaveReqDto.getRoleNm());

        return member;
    }
}
