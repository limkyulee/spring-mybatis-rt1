package com.kyuleelim.admincore.spring.service;

import com.example.chatserver.member.domain.Member;
import com.example.chatserver.member.dto.MemberListResDto;
import com.example.chatserver.member.dto.MemberLoginReqDto;
import com.example.chatserver.member.dto.MemberSaveReqDto;
import com.example.chatserver.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member create(MemberSaveReqDto memberSaveReqDto) {
        if(memberRepository.findByEmail(memberSaveReqDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        Member newMember = Member.builder()
                .username(memberSaveReqDto.getUsername())
                .email(memberSaveReqDto.getEmail())
                .password(passwordEncoder.encode(memberSaveReqDto.getPassword())) // 비밀번호 암호화
                .build();
        Member member = memberRepository.save(newMember);

        return member;
    }

    public Member login(MemberLoginReqDto memberLoginReqDto) {
        Member member = memberRepository.findByEmail(memberLoginReqDto.getEmail()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

        // matches | 자동 암호화
        if(!passwordEncoder.matches(memberLoginReqDto.getPassword(), member.getPassword())){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }


        return member;
    }

    public List<MemberListResDto> findAll() {
        List<Member> member = memberRepository.findAll();
        List<MemberListResDto> memberListResDtos = new ArrayList<>();

        for(Member m : member){
            MemberListResDto memberListResDto = new MemberListResDto();
            memberListResDto.setId(m.getId());
            memberListResDto.setUsername(m.getUsername());
            memberListResDto.setEmail(m.getEmail());
            memberListResDtos.add(memberListResDto);
        }

        return memberListResDtos;
    }
}
