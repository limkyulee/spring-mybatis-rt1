package com.kyuleelim.admincore.member.controller;

import com.kyuleelim.admincore.common.auth.JwtTokenProvider;
import com.kyuleelim.admincore.common.dto.response.CmmResponse;
import com.kyuleelim.admincore.common.dto.response.CmmResponseEntity;
import com.kyuleelim.admincore.member.domain.LoginInfo;
import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberListResDto;
import com.kyuleelim.admincore.member.dto.MemberLoginReqDto;
import com.kyuleelim.admincore.member.dto.MemberSaveReqDto;
import com.kyuleelim.admincore.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author limkyulee
 * @version 1.0 2025.04.27
 * @see 회원 관리 Controller
 */
@Slf4j
@RestController
@Tag(name = "회원 관리", description = "")
@RequestMapping("/api/auth")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * @Method Name join
     * @Description 회원가입
     * @param memberSaveReqDto
     * @return 회원가입 요청 정보
     */
    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/join")
    public CmmResponseEntity<Member> join(@RequestBody @Validated MemberSaveReqDto memberSaveReqDto) {
        log.info("memberSaveReqDto: {}", memberSaveReqDto);
        Member member = memberService.join(memberSaveReqDto);

       return new CmmResponseEntity<>(new CmmResponse<>(member), HttpStatus.OK);
    }

    /**
     * @Method Name login
     * @Description 로그인
     * @param memberLoginReqDto
     * @return 로그인 요청 정보
     */
    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public CmmResponseEntity<LoginInfo> login(@RequestBody @Validated MemberLoginReqDto memberLoginReqDto){
        // email, password 검증
        Member member = memberService.login(memberLoginReqDto);
        log.info("member {}", member);
        // 일차할 경우 access 토큰 발행
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRoleNm().toString());
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(jwtToken);
        loginInfo.setId(member.getMemberId().toString());

        return new CmmResponseEntity<>(new CmmResponse<>(loginInfo), HttpStatus.OK);
    }

    /**
     * @Method Name getMemberList
     * @Description 회원 목록 조회
     * @return 회원 목록
     */
    @Operation(summary = "회원 목록 조회", description = "회원 목록 조회")
    @GetMapping("/members")
    public CmmResponseEntity<MemberListResDto> getMemberList() {
        MemberListResDto memberList = memberService.findAll();

        return new CmmResponseEntity<>(new CmmResponse<>(memberList), HttpStatus.OK);
    }
}
