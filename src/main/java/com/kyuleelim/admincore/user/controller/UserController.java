package com.kyuleelim.admincore.user.controller;

import static com.kyuleelim.admincore.common.enums.CmmConstant.USER_PROFILE;

import com.kyuleelim.admincore.common.domain.CreateGroup;
import com.kyuleelim.admincore.common.domain.DetailGroup;
import com.kyuleelim.admincore.common.domain.UpdateGroup;
import com.kyuleelim.admincore.common.dto.response.CmmResponse;
import com.kyuleelim.admincore.common.dto.response.CmmResponseEntity;
import com.kyuleelim.admincore.common.utils.CommonUtil;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.domain.UserList;
import com.kyuleelim.admincore.user.dto.UserListRequest;
import com.kyuleelim.admincore.user.dto.UserRequest;
import com.kyuleelim.admincore.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author limkyulee
 * @version 1.0 2025.04.27
 * @see 사용자 관리 Controller
 */
@Tag(name = "사용자 관리")
@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Method Name retrieveUserList
     * @Description 사용자 목록 조회
     * @param userListReqDto
     * @return 사용자 목록, 총 건 수
     */
    @Operation(summary = "사용자관리 목록 조회", description = "사용자관리 목록 조회")
    @PostMapping("/retrieveUserList")
    public CmmResponseEntity<UserList> retrieveUserList(@RequestBody UserListRequest userListReqDto) {
        // 사용자 목록 조회 (Service 호출)
        UserList result = userService.retrieveUserList(userListReqDto);

        return new CmmResponseEntity<>(new CmmResponse<>(result), HttpStatus.OK);
    }

    /**
     * @Method Name retrieveUser
     * @Description 사용자 상세 조회
     * @param userReqDto
     * @return 사용자 상세
     */
    @Operation(summary = "사용자관리 상세 조회", description = "사용자관리 상세 조회")
    @PostMapping("/retrieveUser")
    public CmmResponseEntity<User> retrieveUser(@RequestBody @Validated(DetailGroup.class) UserRequest userReqDto) {
        // 사용자 상세 조회 (Service 호출)
        User user = userService.retrieveUser(userReqDto);

        return new CmmResponseEntity<>(new CmmResponse<>(user), HttpStatus.OK);
    }

    /**
     * @Method Name createUser
     * @Description 사용자 등록
     * @param userRequest
     * @return 사용자 등록 성공 여부
     */
    @Operation(summary = "사용자관리 등록", description = "사용자관리 등록")
    @PostMapping("/create")
    public CmmResponseEntity<Void> createUser(@RequestBody @Validated(CreateGroup.class) UserRequest userRequest) {
        // UUID 셋팅
        String uuid = CommonUtil.generateUuid(USER_PROFILE);
        userRequest.setUuid(uuid);

        // 사용자 등록 요청 (Service 호출)
        userService.createUser(userRequest);

        return new CmmResponseEntity<>(new CmmResponse<>(), HttpStatus.OK);
    }

    /**
     * @Method Name updateUser
     * @Description 사용자 수정
     * @param userRequest
     * @return 사용자 수정 성공 여부
     */
    @Operation(summary = "사용자관리 업데이트", description = "사용자관리 업데이트")
    @PostMapping("/update")
    public CmmResponseEntity<Void> updateUser(@RequestBody @Validated(UpdateGroup.class) UserRequest userRequest) {
        // 사용자 수정 요청 (Service 호출)
        userService.updateUser(userRequest);

        return new CmmResponseEntity<>(new CmmResponse<>(), HttpStatus.OK);
    }

    /**
     * @Method Name deleteUser
     * @Description 사용자 삭제
     * @param userRequest
     * @return 사용자 삭제 성공 여부
     */
    @Operation(summary = "사용자관리 삭제", description = "사용자관리 삭제")
    @PostMapping("/delete")
    public CmmResponseEntity<Void> deleteUser(@RequestBody @Validated(DetailGroup.class) UserRequest userRequest) {
        // 사용자 삭제 요청 (Service 호출)
        userService.deleteUser(userRequest);

        return new CmmResponseEntity<>(new CmmResponse<>(), HttpStatus.OK);
    }

    /**
     * @Method downloadUserList
     * @Description 사용자 목록 엑셀 다운로드
     * @param userListRequest
     * @param response
     * @return
     */
    @Operation(summary = "엑셀 다운로드", description = "엑셀 다운로드")
    @PostMapping("/download/userList")
    public void downloadUserList(@RequestBody UserListRequest userListRequest, HttpServletResponse response) {
        // 엑셀 다운로드 요청 (Service 호출)
        userService.downloadUserList(userListRequest, response);
    }
}
