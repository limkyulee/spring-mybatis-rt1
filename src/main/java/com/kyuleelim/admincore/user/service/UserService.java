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
     * @Method Name findAll
     * @Description 사용자 목록 조회
     * @param searchCondition
     * @return PageResponse UserList
     */
    public PageResponse<User> findAll(UserListReqDto searchCondition) {
        List<User> userList = userMapper.findAll(searchCondition);
        int totalCount = userMapper.countAll(searchCondition);

        return new PageResponse<>(userList, totalCount, searchCondition.getCurrentPage(), searchCondition.getLimit());
    }

    /**
     * @Method Name countAll
     * @Description 사용자 목록 전체 수 조회
     * @param searchCondition
     * @return number
     */
    private Integer countAll(UserListReqDto searchCondition) {
        return userMapper.countAll(searchCondition);
    }

    /**
     * @Method Name findById
     * @Description 사용자 상세 조회
     * @param id
     * @return User
     */
    public User findById(Long id) {
        return userMapper.findById(id).orElseThrow(() -> new BizException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * @Method Name insertUser
     * @Description 사용자 등록
     * @Param user
     * @return 사용자 등록여부 (true/false)
     */
    public boolean insertUser(User user) {
        if(userMapper.existsUserById(user.getId())){
            throw new BizException(ErrorCode.DUPLICATE_USER);
        }

        return isSuccess(userMapper.insertUser(user));
    }

    /**
     * @Method Name updateUser
     * @Description 사용자 수정
     * @param user
     * @return 사용자 수정여부 (true/false)
     */
    public boolean updatedUser(UserUpdateReqDto user) {
        if (!userMapper.existsUserById(user.getId())) {
            throw new BizException(ErrorCode.USER_NOT_FOUND);
        }

        return isSuccess(userMapper.updatedUser(user));
    }

    /**
     * @Method Name deleteUser
     * @Description 사용자 삭제
     * @param id
     * @return 사용자 삭제여부 (true/false)
     */
    public boolean deleteUser(Long id) {
        if (!userMapper.existsUserById(id)) {
            throw new BizException(ErrorCode.USER_NOT_FOUND);
        }

        return isSuccess(userMapper.deleteUser(id));
    }

    /**
     * @Method Name isSuccess
     * @Description 등록, 수정, 삭제 성공 여부 반환
     * @param affectedRows
     * @return 성공 여부
     */
    private static boolean isSuccess(int affectedRows){
        return affectedRows > 0;
    }
}
