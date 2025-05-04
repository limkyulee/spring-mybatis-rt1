package com.kyuleelim.admincore.member.mapper;

import com.kyuleelim.admincore.member.domain.Member;
import com.kyuleelim.admincore.member.dto.MemberListResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    void save(Member member);
    Optional<Member> findByEmail(String email);
    List<Member> findAll();
}
