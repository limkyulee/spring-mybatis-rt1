package com.kyuleelim.admincore.user.dao;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListRequest;
import com.kyuleelim.admincore.user.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDao {
    List<User> retrieveUserListWithoutLimit(UserListRequest userListRequest);
    List<User> retrieveUserList(UserListRequest userListRequest);
    Integer retrieveUserListCount(UserListRequest userListRequest);
    User retrieveUser(UserRequest userRequest);
    Integer createUser(UserRequest userRequest);
    Integer updateUser(UserRequest userRequest);
    Integer deleteUser(UserRequest userRequest);
    Integer updatePasswordOnly(UserRequest userRequest);
}
