package com.kyuleelim.admincore.member.dto;

import com.kyuleelim.admincore.member.domain.Member;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResDto {
    private List<Member> list;
    private int totalCount;
}
