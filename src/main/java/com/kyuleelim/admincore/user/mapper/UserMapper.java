package com.kyuleelim.admincore.user.mapper;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    public List<User> findAll(UserListReqDto searchCondition);
    public int countAll(UserListReqDto searchCondition);
    public Optional<User> findById(Long id);
    public int insertUser(UserReqDto user);
    public int updateUser(UserReqDto user);
    public int deleteUser(Long id);
    public boolean existUserById(Long id);
    public boolean existUserByEmail(String email);
}
