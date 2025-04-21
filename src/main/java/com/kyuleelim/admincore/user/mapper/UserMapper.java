package com.kyuleelim.admincore.user.mapper;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.SearchCondition;
import com.kyuleelim.admincore.user.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    void updateUser(@Param("id") Long id, @Param("user") UserRequest user);
    void deleteUser(Long id);
    User findById(Long id);
    List<User> findAll(SearchCondition searchCondition);
    Integer countAll(SearchCondition searchCondition);
}
