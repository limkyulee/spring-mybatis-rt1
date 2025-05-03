package com.kyuleelim.admincore.user.mapper;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserUpdateReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    List<User> findAll(UserListReqDto searchCondition);
    int countAll(UserListReqDto searchCondition);
    Optional<User> findById(Long id);
    int insertUser(User user);
    int updatedUser(UserUpdateReqDto user);
    int deleteUser(Long id);
    boolean existsUserById(Long id);
}
