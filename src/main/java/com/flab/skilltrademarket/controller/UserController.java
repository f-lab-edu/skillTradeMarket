package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.user.dto.SignupRequest;
import com.flab.skilltrademarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 이메일 중복확인
     *
     * @param email 이메일
     */
    @GetMapping("stm/check-email")
    public void checkDuplicateEmail(@RequestParam("email") String email) {
        userService.checkDuplicateEmail(email);
    }

    /**
     * 닉네임 중복확인
     *
     * @param nickName 닉네임
     */
    @GetMapping("stm/check-nickName")
    public void checkDuplicateNickName(@RequestParam("nickName") String nickName) {
        userService.checkDuplicateNickName(nickName);
    }

    /**
     * 회원가입
     *
     * @param request 회원가입할 entity
     */
    @PostMapping("stm/signup")
    public void signUp(@RequestBody SignupRequest request) {
        userService.save(request);
    }


}
