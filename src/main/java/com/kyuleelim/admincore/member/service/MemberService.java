package com.kyuleelim.admincore.member.service;

import com.kyuleelim.admincore.common.enums.ErrorCode;
import com.kyuleelim.admincore.common.exception.BizException;
import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberListResDto;
import com.kyuleelim.admincore.member.dto.MemberLoginReqDto;
import com.kyuleelim.admincore.member.dto.MemberSaveReqDto;
import com.kyuleelim.admincore.member.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class MemberService {

    @Autowired
    private final MemberMapper memberMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    /**
     * @method join
     * @name 회원가입
     * @param memberSaveReqDto
     * @return member
     */
    public Member join(MemberSaveReqDto memberSaveReqDto) {
        validateDuplicateEmail(memberSaveReqDto.getEmail());

        Member member = createMember(memberSaveReqDto);
        log.info("member {}", member);
        memberMapper.save(member);

        return member;
    }

    /**
     * @method validateDuplicateEmail
     * @name 이메일 중복 검사
     * @param email
     */
    private void validateDuplicateEmail(String email) {
        memberMapper.findByEmail(email)
                .ifPresent(m -> {
                    throw new BizException(ErrorCode.DUPLICATE_EMAIL);
                });
    }

    /**
     * @method createMember
     * @name 회원가입을 위한 멤버 생성
     * @param memberSaveReqDto
     * @return member
     */
    private Member createMember(MemberSaveReqDto memberSaveReqDto) {
        Member member = new Member();
        member.setUsername(memberSaveReqDto.getUsername());
        member.setEmail(memberSaveReqDto.getEmail());
        member.setPassword(passwordEncoder.encode(memberSaveReqDto.getPassword()));
        member.setRole(memberSaveReqDto.getRole());

        return member;
    }

    /**
     * @method login
     * @name 로그인
     * @param memberLoginReqDto
     * @return member
     */
    public Member login(MemberLoginReqDto memberLoginReqDto) {
        Member member = memberMapper.findByEmail(memberLoginReqDto.getEmail())
                .orElseThrow(() -> new BizException(ErrorCode.USER_NOT_FOUND));

        log.info("로그인 시도 email: {}", memberLoginReqDto.getEmail());
        log.info("입력된 비밀번호(plain): {}", memberLoginReqDto.getPassword());
        log.info("DB에서 읽은 비밀번호(encoded): {}", member.getPassword());

        // matches | 자동 암호화
        if(!passwordEncoder.matches(memberLoginReqDto.getPassword(), member.getPassword())){
            throw  new BizException(ErrorCode.WRONG_PASSWORD);
        }

        return member;
    }

    /**
     * @method findAll
     * @name 회원가입한 멤버 전체 조회
     * @return memberListResDtos
     */
    public List<MemberListResDto> findAll() {
        List<Member> members = memberMapper.findAll();
        List<MemberListResDto> memberListResDtos = new ArrayList<>();
        for (Member member : members) {
            MemberListResDto memberListResDto = new MemberListResDto();
            memberListResDto.setId(member.getId());
            memberListResDto.setUsername(member.getUsername());
            memberListResDto.setEmail(member.getEmail());

            memberListResDtos.add(memberListResDto);
        }
        return memberListResDtos;
    }
}
