package com.kyuleelim.admincore.user.service;

import com.kyuleelim.admincore.common.domain.PageResponse;
import com.kyuleelim.admincore.common.enums.ErrorCode;
import com.kyuleelim.admincore.common.exception.BizException;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.SearchCondition;
import com.kyuleelim.admincore.user.dto.UserRequest;
import com.kyuleelim.admincore.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // 사용자 목록 조회
    public PageResponse<User> findAll(SearchCondition searchCondition) {
        List<User> userList = userMapper.findAll(searchCondition);
        int totalCount = userMapper.countAll(searchCondition);
        return new PageResponse<>(userList, totalCount, searchCondition.getCurrentPage(), searchCondition.getLimit());
    }

    // 전체 사용자 수 조회
    private Integer countAll(SearchCondition searchCondition) {
        return userMapper.countAll(searchCondition);
    }

    // 사용자 상세 조회
    public User findById(Long id) {
        return userMapper.findById(id)
                .orElseThrow(() -> new BizException(ErrorCode.USER_NOT_FOUND));
    }

    // 사용자 등록
    public void insertUser(User user) {
        try{
            userMapper.insertUser(user);
        }catch (DuplicateKeyException e){
            throw new BizException(ErrorCode.DUPLICATE_USER);
        }
    }

    // 사용자 업데이트
    public void updateUser(Long id, UserRequest user) {
        if (userMapper.findById(id).isEmpty()) {
            throw new BizException(ErrorCode.USER_NOT_FOUND);
        }

        try {
            userMapper.updateUser(id, user);
        } catch (Exception e) {
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        findById(id);

        try {
            userMapper.deleteUser(id);
        } catch (Exception e) {
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}
