package com.kyuleelim.admincore.user.mapper;

import com.kyuleelim.admincore.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
}
