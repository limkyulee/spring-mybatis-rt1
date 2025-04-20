package com.kyuleelim.admincore.user.service;

import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }
}
