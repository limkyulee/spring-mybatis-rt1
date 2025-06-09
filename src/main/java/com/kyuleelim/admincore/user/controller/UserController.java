package com.kyuleelim.admincore.user.controller;

import com.kyuleelim.admincore.common.domain.CreateGroup;
import com.kyuleelim.admincore.common.domain.DetailGroup;
import com.kyuleelim.admincore.common.domain.UpdateGroup;
import com.kyuleelim.admincore.common.dto.response.CmmResponse;
import com.kyuleelim.admincore.common.dto.response.CmmResponseEntity;
import com.kyuleelim.admincore.common.dto.response.PageResponse;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserReqDto;
import com.kyuleelim.admincore.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author limkyulee
 * @version 1.0 2025.04.27
 * @see 사용자 관리 Controller
 */
@RestController
@Tag(name = "사용자 관리", description = "")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Method Name findAll
     * @Description 사용자 목록 조회
     * @param userListReqDto
     * @return 사용자 목록
     */
    @Operation(summary = "사용자관리 목록 조회", description = "사용자관리 목록 조회")
    @PostMapping("/list")
    public CmmResponseEntity<PageResponse<User>> findAll(@RequestBody UserListReqDto userListReqDto) {
        PageResponse<User> result = userService.findAll(userListReqDto);

        return new CmmResponseEntity<>(new CmmResponse<>(result), HttpStatus.OK);
    }

    /**
     * @Method Name findById
     * @Description 사용자 상세 조회
     * @param userReqDto
     * @return 사용자 상세 조회 값
     */
    @Operation(summary = "사용자관리 상세 조회", description = "사용자관리 상세 조회")
    @PostMapping("/detail")
    public CmmResponseEntity<User> findById(@RequestBody @Validated(DetailGroup.class) UserReqDto userReqDto) {
        User result = userService.findById(userReqDto.getUserId());

        return new CmmResponseEntity<>(new CmmResponse<>(result), HttpStatus.OK);
    }

    /**
     * @Method Name insertUser
     * @Description 사용자 등록
     * @param userReqDto
     * @return 사용자 등록 요청 값
     */
    @Operation(summary = "사용자관리 등록", description = "사용자관리 등록")
    @PostMapping("/insert")
    public CmmResponseEntity<UserReqDto> insertUser(@RequestBody @Validated(CreateGroup.class) UserReqDto userReqDto) {
        userService.insertUser(userReqDto);

        return new CmmResponseEntity<>(new CmmResponse<>(userReqDto), HttpStatus.OK);
    }

    /**
     * @Method Name updateUser
     * @Description 사용자 수정
     * @param userReqDto
     * @return 사용자 수정 요청 값
     */
    @Operation(summary = "사용자관리 업데이트", description = "사용자관리 업데이트")
    @PostMapping("/update")
    public CmmResponseEntity<UserReqDto> updateUser(@RequestBody @Validated(UpdateGroup.class) UserReqDto userReqDto) {
        userService.updateUser(userReqDto);

        return new CmmResponseEntity<>(new CmmResponse<>(userReqDto), HttpStatus.OK);
    }

    /**
     * @Method Name deleteUser
     * @Description 사용자 삭제
     * @param userReqDto
     * @return 사용자 삭제 요청 값
     */
    @Operation(summary = "사용자관리 삭제", description = "사용자관리 삭제")
    @PostMapping("/delete")
    public CmmResponseEntity<Long> deleteUser(@RequestBody @Validated(DetailGroup.class) UserReqDto userReqDto) {
        Long id = userReqDto.getUserId();
        userService.deleteUser(id);

        return new CmmResponseEntity<>(new CmmResponse<>(id), HttpStatus.OK);
    }
}
