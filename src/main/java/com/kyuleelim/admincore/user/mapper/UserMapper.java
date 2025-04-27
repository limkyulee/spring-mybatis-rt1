package com.kyuleelim.admincore.user.mapper;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserUpdateReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    void updateUser(@Param("id") Long id, @Param("user") UserUpdateReqDto user);
    void deleteUser(Long id);
    Optional<User> findById(Long id);
    List<User> findAll(UserListReqDto searchCondition);
    Integer countAll(UserListReqDto searchCondition);
}
