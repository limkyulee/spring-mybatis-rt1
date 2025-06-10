package com.kyuleelim.admincore.user.mapper;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> retrieveUserList(UserListReqDto userListReqDto);
    int retrieveUserListCount(UserListReqDto userListReqDto);
    User retrieveUser(UserReqDto userReqDto);
    int createUser(UserReqDto userReqDto);
    int updateUser(UserReqDto userReqDto);
    int deleteUser(UserReqDto userReqDto);

    boolean existUserByEmail(String email);
}
