package com.kyuleelim.admincore.member.controller;

import com.kyuleelim.admincore.common.auth.JwtTokenProvider;
import com.kyuleelim.admincore.common.dto.response.CmmResponse;
import com.kyuleelim.admincore.common.dto.response.CmmResponseEntity;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author limkyulee
 * @version 1.0 2025.04.27
 * @description 회원 관리 (회원가입, 로그인, 전체 조회)
 */

@Slf4j
@RestController
@Tag(name = "멤버 관리", description = "")
@RequestMapping("/api/auth")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * @method join
     * @name 회원가입
     * @param memberSaveReqDto
     * @return Member, 200
     */
    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<CmmResponse<Member>> join(@RequestBody MemberSaveReqDto memberSaveReqDto) {
       log.info("memberSaveReqDto: {}", memberSaveReqDto);
        Member member = memberService.join(memberSaveReqDto);

       return new CmmResponseEntity<>(new CmmResponse<>(member), HttpStatus.OK);
    }

    /**
     * @method login
     * @name 로그인
     * @param memberLoginReqDto
     * @return loginInfo, 200
     */
    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<CmmResponse<Map<String, Object>>> login(@RequestBody MemberLoginReqDto memberLoginReqDto){
        // email, password 검증
        Member member = memberService.login(memberLoginReqDto);
        // 일차할 경우 access 토큰 발행
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("token", jwtToken);
        loginInfo.put("id", member.getId());

        return new CmmResponseEntity<>(new CmmResponse<>(loginInfo), HttpStatus.OK);
    }

    /**
     * @method getMemberList
     * @name 멤버 리스트 조회
     * @return memberList
     */
    @Operation(summary = "멤버 리스트 조회", description = "멤버 리스트 조회")
    @GetMapping("/members")
    public ResponseEntity<CmmResponse<List<MemberListResDto>>> getMemberList() {
        List<MemberListResDto> memberList = memberService.findAll();
        log.info("memberList: {}", memberList);
        return new CmmResponseEntity<>(new CmmResponse<>(memberList), HttpStatus.OK);
    }
}
