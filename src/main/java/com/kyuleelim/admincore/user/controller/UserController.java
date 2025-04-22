package com.kyuleelim.admincore.user.controller;

import com.kyuleelim.admincore.common.dto.PageResponse;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.SearchCondition;
import com.kyuleelim.admincore.user.dto.UserRequest;
import com.kyuleelim.admincore.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자 목록 조회
     * @param searchCondition
     * @return List<User>
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/list")
    public PageResponse<User> findAll(@RequestBody SearchCondition searchCondition) {
        List<User> users = userService.findAll(searchCondition);
        int totalCount = userService.countAll(searchCondition);
        return new PageResponse<>(users, totalCount, searchCondition.getCurrentPage(), searchCondition.getLimit());
    }

    /**
     * 사용자 상세 조회
     * @param id
     * @return User
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/detail/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * 사용자 등록
     * @param user
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/insert")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    /**
     * 사용자 업데이트
     * @param user, id
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Validated UserRequest user) {
        userService.updateUser(id, user);
    }

    /**
     * 사용자 삭제
     * @param id
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
