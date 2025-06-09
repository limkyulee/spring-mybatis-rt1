package com.kyuleelim.admincore.member.dto;

import com.kyuleelim.admincore.member.domain.Member;
import java.util.List;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MemberList")
public class MemberList {
    private List<Member> list;
    private int totalCount;
}
