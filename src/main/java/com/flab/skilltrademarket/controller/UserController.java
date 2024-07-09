package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.user.dto.SignupRequest;
import com.flab.skilltrademarket.global.response.CommonResponse;
import com.flab.skilltrademarket.service.UserService;
import jakarta.validation.Valid;
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
    @GetMapping("/user/email")
    public CommonResponse<Void> checkDuplicateEmail(@RequestParam("email") String email) {
        userService.checkDuplicateEmail(email);
        return CommonResponse.success();
    }

    /**
     * 닉네임 중복확인
     *
     * @param nickName 닉네임
     */
    @GetMapping("/user/nickName")
    public CommonResponse<Void> checkDuplicateNickName(@RequestParam("nickName") String nickName) {
        userService.checkDuplicateNickName(nickName);
        return CommonResponse.success();
    }

    /**
     * 회원가입
     *
     * @param request 회원가입할 entity
     */
    @PostMapping("/user/signup")
    public CommonResponse<Void> signUp(@Valid @RequestBody SignupRequest request) {
        userService.save(request);
        return CommonResponse.success();
    }


}
