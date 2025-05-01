package com.kyuleelim.admincore.user.controller;

import com.kyuleelim.admincore.common.controller.ApiBaseController;
import com.kyuleelim.admincore.common.dto.response.CmmResponse;
import com.kyuleelim.admincore.common.dto.response.CmmResponseEntity;
import com.kyuleelim.admincore.common.dto.response.PageResponse;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.UserListReqDto;
import com.kyuleelim.admincore.user.dto.UserUpdateReqDto;
import com.kyuleelim.admincore.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author limkyulee
 * @version 1.0 2025.04.27
 * @description 사용자 관리
 */

@RestController
@Tag(name = "사용자 관리", description = "")
@RequestMapping("/users")
public class UserController extends ApiBaseController {

    @Autowired
    private UserService userService;

    /**
     * @method findAll
     * @name 사용사 목록 조회
     * @param userListReqDto
     * @return List<User>
     */
    @Operation(summary = "사용자관리 목록 조회", description = "사용자관리 목록 조회")
    @PostMapping("/list")
    public ResponseEntity<CmmResponse<PageResponse<User>>> findAll(@RequestBody UserListReqDto userListReqDto) {
        PageResponse<User> result = userService.findAll(userListReqDto);

        return new CmmResponseEntity<>(new CmmResponse<>(result), HttpStatus.OK);
    }

    /**
     * @method findById
     * @name 사용자 상세 조회
     * @param id
     * @return User
     */
    @Operation(summary = "사용자관리 상세 조회", description = "사용자관리 상세 조회")
    @PostMapping("/detail/{id}")
    public ResponseEntity<CmmResponse<User>> findById(@PathVariable Long id) {
        User result = userService.findById(id);
        return new CmmResponseEntity<>(new CmmResponse<>(result), HttpStatus.OK);
    }

    // TODO : validaion
    /**
     * @method insertUser
     * @name 사용자 등록
     * @param user
     * @return
     */
    @Operation(summary = "사용자관리 등록", description = "사용자관리 등록")
    @PostMapping("/insert")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    /**
     * @method updateUser
     * @name 사용자 업데이트
     * @param id
     * @param userUpdateReqDto
     * @return
     */
    @Operation(summary = "사용자관리 업데이트", description = "사용자관리 업데이트")
    @PostMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Validated UserUpdateReqDto userUpdateReqDto) {
        userService.updateUser(id, userUpdateReqDto);
    }

    /**
     * @method deleteUser
     * @name 사용자 삭제
     * @param id
     * @return
     */
    @Operation(summary = "사용자관리 삭제", description = "사용자관리 삭제")
    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
