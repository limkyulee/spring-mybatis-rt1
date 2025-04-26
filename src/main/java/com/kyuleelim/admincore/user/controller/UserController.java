package com.kyuleelim.admincore.user.controller;

import com.kyuleelim.admincore.common.domain.CmmResponse;
import com.kyuleelim.admincore.common.domain.CmmResponseEntity;
import com.kyuleelim.admincore.common.domain.PageResponse;
import com.kyuleelim.admincore.common.utils.ResponseUtils;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.SearchCondition;
import com.kyuleelim.admincore.user.dto.UserRequest;
import com.kyuleelim.admincore.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author limkyulee
 * @version 0.
 * @see {사용자관리}
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * @method findAll
     * @name 사용사 목록 조회
     * @param searchCondition
     * @return List<User>
     */
    @PostMapping("/list")
    public ResponseEntity<CmmResponse<PageResponse<User>>> findAll(@RequestBody SearchCondition searchCondition) {
        PageResponse<User> result = userService.findAll(searchCondition);

        return new CmmResponseEntity<>(new CmmResponse<>(result), HttpStatus.OK);
    }

    /**
     * @method findById
     * @name 사용자 상세 조회
     * @param id
     * @return User
     */
    @PostMapping("/detail/{id}")
    public ResponseEntity<CmmResponse<User>> findById(@PathVariable Long id) {
        User result = userService.findById(id);
        return new CmmResponseEntity<>(new CmmResponse<>(result), HttpStatus.OK);
    }

    /**
     * @method insertUser
     * @name 사용자 등록
     * @param user
     * @return
     */

    // TODO : validaion
    @PostMapping("/insert")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    /**
     * @method updateUser
     * @name 사용자 업데이트
     * @param user, id
     * @return
     */
    @PostMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Validated UserRequest user) {
        userService.updateUser(id, user);
    }

    /**
     * @method deleteUser
     * @name 사용자 삭제
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
