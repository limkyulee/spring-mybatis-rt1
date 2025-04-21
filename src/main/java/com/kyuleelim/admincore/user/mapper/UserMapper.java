package com.kyuleelim.admincore.user.mapper;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    void updateUser(@Param("user") User user, @Param("id") Long id);
    void deleteUser(Long id);
    User findById(Long id);
    List<User> findAll(SearchCondition searchCondition);
    Integer countAll(SearchCondition searchCondition);
}
