package com.kyuleelim.admincore.user.domain;

import lombok.Data;

import java.util.List;

/**
 * ===========================================
 * Project        : com.kyuleelim.admincore.user.domain
 * File Name      : UserList
 * Author         : pneum
 * Created Date   : 2025-06-10 오후 11:26
 * Updated Date   : 2025-06-10 오후 11:26
 * Description    : 사용자 목록 조회 응답
 * ===========================================
 */
@Data
public class UserList {
    private List<User> list;
    private int totalCount;
}
