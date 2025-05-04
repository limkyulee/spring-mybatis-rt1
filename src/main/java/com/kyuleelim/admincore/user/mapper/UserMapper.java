package com.kyuleelim.admincore.user.mapper;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    List<User> findAll(UserListReqDto searchCondition);
    int countAll(UserListReqDto searchCondition);
    Optional<User> findById(Long id);
    int insertUser(UserReqDto user);
    int updateUser(UserReqDto user);
    int deleteUser(Long id);
    boolean existUserById(Long id);
    boolean existUserByEmail(String email);
}
