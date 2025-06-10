package com.kyuleelim.admincore.user.service;

import com.kyuleelim.admincore.common.dto.response.PageResponse;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.domain.UserList;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;

public interface UserService {
    UserList retrieveUserList(UserListReqDto userListReqDto);
    User retrieveUser(UserReqDto userReqDto);
    void createUser(UserReqDto userReqDto);
    void updateUser(UserReqDto userReqDto);
    void deleteUser(UserReqDto userReqDto);
}
