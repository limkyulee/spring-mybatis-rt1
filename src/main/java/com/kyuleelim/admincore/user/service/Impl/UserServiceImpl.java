package com.kyuleelim.admincore.user.service.Impl;

import com.kyuleelim.admincore.common.exception.BizException;
import com.kyuleelim.admincore.common.utils.ExcelUtil;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.domain.UserList;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;
import com.kyuleelim.admincore.user.mapper.UserMapper;
import com.kyuleelim.admincore.user.service.UserService;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author limkyulee
 * @version 1.0, 5/4/25
 * @see 사용자 관리 Service Impl
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Method Name retrieveUserList
     * @Description 사용자 목록 조회
     * @param userListReqDto
     * @return 사용자 목록
     */
    @Transactional(readOnly = true)
    @Override
    public UserList retrieveUserList(UserListReqDto userListReqDto) {
        // 사용자 목록 조회 요청 (Dao 호출)
        List<User> list = userMapper.retrieveUserList(userListReqDto);
        // 사용자 목록 셋팅
        UserList userList = new UserList();
        userList.setList(list);
        // 사용자 목록 총 건 수 조회 (Dao 호출)
        int totalCount = userMapper.retrieveUserListCount(userListReqDto);
        // 사용자 목록 총 건 수 셋팅
        userList.setTotalCount(totalCount);

        return userList;
    }

    /**
     * @Method Name retrieveUser
     * @Description 사용자 상세 조회
     * @param userReqDto
     * @return User
     */
    @Transactional(readOnly = true)
    @Override
    public User retrieveUser(UserReqDto userReqDto) {

        // 사용자 상세 조회 요청 (Dao 호출)
        return userMapper.retrieveUser(userReqDto);
    }

    /**
     * @Method Name createUser
     * @Description 사용자 등록
     * @param userReqDto
     */
    @Transactional
    @Override
    public void createUser(UserReqDto userReqDto) {
        // 사용자 이메일 중복 체크 예외 처리
        if(userMapper.existUserByEmail(userReqDto.getEmail())){
            throw new BizException("DUPLICATE_EMAIL");
        }

        // 사용자 등록 요청 (Dao 호출)
        int affectedRow = userMapper.createUser(userReqDto);
        // 사용자 등록 실패 예외 처리
        if(affectedRow < 1) {
            throw new BizException("FAIL_ADD", List.of("사용자"));
        }

    }

    /**
     * @Method Name updateUser
     * @Description 사용자 수정
     * @param userReqDto
     */
    @Transactional
    @Override
    public void updateUser(UserReqDto userReqDto) {
        // 사용자 수정 요청 (Dao 호출)
        int affectedRow = userMapper.updateUser(userReqDto);
        // 사용자 수정 실패 예외 처리
        if(affectedRow < 1) {
            throw new BizException("FAIL_UPDATE", List.of("사용자"));
        }
    }

    /**
     * @Method Name deleteUser
     * @Description 사용자 삭제
     * @param userReqDto
     */
    @Transactional
    @Override
    public void deleteUser(UserReqDto userReqDto) {
        int affectedRow = userMapper.deleteUser(userReqDto);

        if(affectedRow < 1) {
            throw new BizException("FAIL_DELETE", List.of("사용자"));
        }
    }

    /**
     * @Method downloadUserList
     * @Description 엑셀 다운로드
     * @param userListReqDto
     * @param response
     */
    @Override
    public void downloadUserList(UserListReqDto userListReqDto, HttpServletResponse response) {
        // 다운로드할 사용자 데이터 목록 조회 (Dao 호출)
        List<User> list = userMapper.retrieveAllUserList(userListReqDto);
        log.info("list {}", list);
        // 헤더 생성
        Map<String, String> headerMap = Map.of(
                "userId","아이디",
                "userNm","이름",
                "email","이메일"
        );

        // 엑셀 다운르도 실행
        ExcelUtil.downloadExcel(response, "user_list",list, headerMap);
    }
}
