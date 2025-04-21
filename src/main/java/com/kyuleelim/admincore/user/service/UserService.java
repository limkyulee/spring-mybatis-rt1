package com.kyuleelim.admincore.user.service;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.SearchCondition;
import com.kyuleelim.admincore.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // 사용자 목록 조회
    public List<User> findAll(SearchCondition searchCondition) {
        return userMapper.findAll(searchCondition);
    }

    // 전체 사용자 수 조회
    public Integer countAll(SearchCondition searchCondition) {
        return userMapper.countAll(searchCondition);
    }

    // 사용자 상세 조회
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    // 사용자 등록
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    // 사용자 업데이트
    public void updateUser(User user, Long id) {
        userMapper.updateUser(user, id);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

}
