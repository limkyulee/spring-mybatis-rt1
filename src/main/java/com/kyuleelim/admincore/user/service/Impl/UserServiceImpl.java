package com.kyuleelim.admincore.user.service.Impl;

import com.kyuleelim.admincore.common.dto.response.PageResponse;
import com.kyuleelim.admincore.common.exception.BizException;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;
import com.kyuleelim.admincore.user.mapper.UserMapper;
import com.kyuleelim.admincore.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author limkyulee
 * @version 1.0, 5/4/25
 * @see 사용자 관리 Service Impl
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Method Name findAll
     * @Description 사용자 목록 조회
     * @param searchCondition
     * @return 사용자 목록
     */
    public PageResponse<User> findAll(UserListReqDto searchCondition) {
        List<User> userList = userMapper.findAll(searchCondition);
        int totalCount = userMapper.countAll(searchCondition);

        return new PageResponse<>(userList, totalCount, searchCondition.getCurrentPage(), searchCondition.getLimit());
    }

    /**
     * @Method Name countAll
     * @Description 전체 사용자 수 조회
     * @param searchCondition
     * @return 전체 사용자 수
     */
    private int countAll(UserListReqDto searchCondition) {
        return userMapper.countAll(searchCondition);
    }

    /**
     * @Method Name findById
     * @Description 사용자 상세 조회
     * @param id
     * @return User
     */
    public User findById(Long id) {
        return userMapper.findById(id).orElseThrow(() -> new BizException("user_not_found"));
    }

    /**
     * @Method Name insertUser
     * @Description 사용자 등록
     * @param userReqDto
     * @return 사용자 등록여부 (true/false)
     */
    public boolean insertUser(UserReqDto userReqDto) {
        if(userMapper.existUserByEmail(userReqDto.getEmail())){
            throw new BizException("duplicate_user");
        }

        return isSuccess(userMapper.insertUser(userReqDto));
    }

    /**
     * @Method Name updateUser
     * @Description 사용자 수정
     * @param userReqDto
     * @return 사용자 수정여부 (true/false)
     */
    public boolean updateUser(UserReqDto userReqDto) {
        if (!userMapper.existUserById(userReqDto.getUserId())) {
            throw new BizException("user_not_found");
        }

        return isSuccess(userMapper.updateUser(userReqDto));
    }

    /**
     * @Method Name deleteUser
     * @Description 사용자 삭제
     * @param id
     * @return 사용자 삭제여부 (true/false)
     */
    public boolean deleteUser(Long id) {
        if (!userMapper.existUserById(id)) {
            throw new BizException("user_not_found");
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
