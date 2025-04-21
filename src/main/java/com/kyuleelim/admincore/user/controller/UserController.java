package com.kyuleelim.admincore.user.controller;

import com.kyuleelim.admincore.common.dto.PageResponse;
import com.kyuleelim.admincore.user.domain.User;
import com.kyuleelim.admincore.user.dto.SearchCondition;
import com.kyuleelim.admincore.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/detail/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * 사용자 등록
     * @param user
     * @return
     */
    @PostMapping("/insert")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    /**
     * 사용자 업데이트
     * @param user, id
     * @return
     */
    @PostMapping("/update/{id}")
    public void updaetUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(user, id);
    }

    /**
     * 사용자 삭제
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
}
