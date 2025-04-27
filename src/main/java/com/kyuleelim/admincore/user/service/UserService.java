package com.kyuleelim.admincore.user.service;

import com.kyuleelim.admincore.common.dto.response.PageResponse;
import com.kyuleelim.admincore.common.enums.ErrorCode;
import com.kyuleelim.admincore.common.exception.BizException;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserUpdateReqDto;
import com.kyuleelim.admincore.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /**
     * @method findAll
     * @name 사용자 목록 조회
     * @param searchCondition
     * @return PageResponse UserList
     */
    public PageResponse<User> findAll(UserListReqDto searchCondition) {
        List<User> userList = userMapper.findAll(searchCondition);
        int totalCount = userMapper.countAll(searchCondition);
        return new PageResponse<>(userList, totalCount, searchCondition.getCurrentPage(), searchCondition.getLimit());
    }

    /**
     * @method countAll
     * @name 사용자 목록 전체 수 조회
     * @param searchCondition
     * @return number
     */
    private Integer countAll(UserListReqDto searchCondition) {
        return userMapper.countAll(searchCondition);
    }

    /**
     * @method findById
     * @name 아이디로 사용자 상세 조회
     * @param id
     * @return User
     */
    public User findById(Long id) {
        return userMapper.findById(id)
                .orElseThrow(() -> new BizException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * @method insertUser
     * @name 사용자 등록
     * @param user
     */
    public void insertUser(User user) {
        try{
            userMapper.insertUser(user);
        }catch (DuplicateKeyException e){
            throw new BizException(ErrorCode.DUPLICATE_USER);
        }
    }

    /**
     * @method updateUser
     * @name 사용자 업데이트
     * @param id
     * @param user
     */
    public void updateUser(Long id, UserUpdateReqDto user) {
        if (userMapper.findById(id).isEmpty()) {
            throw new BizException(ErrorCode.USER_NOT_FOUND);
        }

        try {
            userMapper.updateUser(id, user);
        } catch (Exception e) {
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @method deleteUser
     * @name 사용자 삭제처리
     * @param id
     */
    public void deleteUser(Long id) {
        findById(id);

        try {
            userMapper.deleteUser(id);
        } catch (Exception e) {
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
