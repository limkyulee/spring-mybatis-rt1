package com.kyuleelim.admincore.user.service;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.domain.UserList;
import com.kyuleelim.admincore.user.dto.UserListRequest;
import com.kyuleelim.admincore.user.dto.UserRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    UserList retrieveUserList(UserListRequest userListReqDto);
    User retrieveUser(UserRequest userReqDto);
    void createUser(UserRequest userReqDto);
    void updateUser(UserRequest userReqDto);
    void deleteUser(UserRequest userReqDto);
    void downloadUserList(UserListRequest userListReqDto, HttpServletResponse response);
}
