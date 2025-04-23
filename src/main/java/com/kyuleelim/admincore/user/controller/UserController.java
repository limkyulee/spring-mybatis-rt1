package com.kyuleelim.admincore.user.controller;

import com.kyuleelim.admincore.common.dto.CmmResponse;
import com.kyuleelim.admincore.common.dto.PageResponse;
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
 * @version 0.1
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * @method 사용사 목록 조회
     * @param searchCondition
     * @return List<User>
     */
    @PostMapping("/list")
    public ResponseEntity<CmmResponse<PageResponse<User>>> findAll(@RequestBody SearchCondition searchCondition) {
        PageResponse<User> result = userService.findAll(searchCondition);

        return ResponseUtils.ok(result);
    }

    /**
     * @method 사용자 상세 조회
     * @param id
     * @return User
     */
    @PostMapping("/detail/{id}")
    public CmmResponse<User> findById(@PathVariable Long id) {
        User result = userService.findById(id);
        return CmmResponse.ok(HttpStatus.OK, result);
    }

    /**
     * @method 사용자 등록
     * @param user
     * @return
     */

    // TODO : validaion
    @PostMapping("/insert")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    /**
     * @method 사용자 업데이트
     * @param user, id
     * @return
     */
    @PostMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Validated UserRequest user) {
        userService.updateUser(id, user);
    }

    /**
     * @method 사용자 삭제
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
