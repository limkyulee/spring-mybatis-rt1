package com.kyuleelim.admincore.spring.controller;

import com.kyuleelim.admincore.common.auth.JwtTokenProvider;
import com.kyuleelim.admincore.spring.domain.Member;
import com.kyuleelim.admincore.spring.dto.MemberListResDto;
import com.kyuleelim.admincore.spring.dto.MemberLoginReqDto;
import com.kyuleelim.admincore.spring.dto.MemberSaveReqDto;
import com.kyuleelim.admincore.spring.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto memberSaveReqDto) {
       Member member = memberService.create(memberSaveReqDto);
       return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody MemberLoginReqDto memberLoginReqDto){
        // email, password 검증
        Member member = memberService.login(memberLoginReqDto);
        // 일차할 경우 access 토큰 발행
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("token", jwtToken);
        loginInfo.put("id", member.getId());

        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> memberList() {
        List<MemberListResDto> dtos = memberService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
