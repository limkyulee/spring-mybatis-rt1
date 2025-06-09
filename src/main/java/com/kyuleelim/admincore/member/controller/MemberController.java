package com.kyuleelim.admincore.member.controller;

import com.kyuleelim.admincore.common.auth.JwtTokenProvider;
import com.kyuleelim.admincore.common.dto.response.CmmResponse;
import com.kyuleelim.admincore.common.dto.response.CmmResponseEntity;
import com.kyuleelim.admincore.member.domain.LoginInfo;
import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberList;
import com.kyuleelim.admincore.member.dto.MemberLogin;
import com.kyuleelim.admincore.member.dto.MemberSave;
import com.kyuleelim.admincore.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author limkyulee
 * @version 1.0 2025.04.27
 * @see 회원 관리 Controller
 */
@Tag(name = "회원 관리", description = "")
@RequestMapping("/api/auth")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * @Method Name retrieveMemberList
     * @Description 회원 목록 조회
     * @return 회원 목록, 총 건 수
     */
    @Operation(summary = "회원 목록 조회", description = "회원 목록 조회")
    @PostMapping("/retrieveMemberList")
    @ResponseBody
    public CmmResponseEntity<MemberList> retrieveMemberList() {
        // 회원 목록 조회 (Service 호출)
        MemberList memberList = memberService.retrieveMemberList();

        return new CmmResponseEntity<>(new CmmResponse<>(memberList), HttpStatus.OK);
    }

    /**
     * @Method Name join
     * @Description 회원가입
     * @param memberSave
     * @return 회원가입 성공 여부
     */
    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/join")
    @ResponseBody
    public CmmResponseEntity<Void> join(@RequestBody @Validated MemberSave memberSave) {
        // 회원가입 실행 (Service 호출)
        memberService.createMember(memberSave);

        return new CmmResponseEntity<>(new CmmResponse<>(), HttpStatus.OK);
    }

    /**
     * @Method Name login
     * @Description 로그인
     * @param memberLogin
     * @return 로그인 정보, JWT 토큰
     */
    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    @ResponseBody
    public CmmResponseEntity<LoginInfo> login(@RequestBody @Validated MemberLogin memberLogin){
        // 사용자 로그인 요청 (Service 호출)
        Member member = memberService.login(memberLogin);

        // 로그인 정보 셋팅
        LoginInfo loginInfo = new LoginInfo();

        // JWT 토큰 발행 후 셋팅
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
        loginInfo.setToken(jwtToken);

        // 로그인 ID 셋팅
        String id = member.getId().toString();
        loginInfo.setId(id);

        return new CmmResponseEntity<>(new CmmResponse<>(loginInfo), HttpStatus.OK);
    }


}
