package com.kyuleelim.admincore.user.service;

import com.kyuleelim.admincore.common.dto.response.PageResponse;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;

public interface UserService {
    public PageResponse<User> findAll(UserListReqDto searchCondition);
    public int countAll(UserListReqDto searchCondition);
    public User findById(Long id);
    public boolean insertUser(UserReqDto userReqDto);
    public boolean updateUser(UserReqDto userReqDto);
    public boolean deleteUser(Long id);
}
