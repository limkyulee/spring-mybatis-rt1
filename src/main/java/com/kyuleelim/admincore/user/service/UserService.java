package com.kyuleelim.admincore.user.service;

import com.kyuleelim.admincore.common.dto.response.PageResponse;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;

public interface UserService {
    PageResponse<User> findAll(UserListReqDto searchCondition);
    User findById(Long id);
    boolean insertUser(UserReqDto userReqDto);
    boolean updateUser(UserReqDto userReqDto);
    boolean deleteUser(Long id);
}
