package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.user.dto.SignupRequest;
import com.flab.skilltrademarket.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@Tag(name = "유저", description = "유저 회원가입, 이메일 중복확인, 닉네임 중복확인 API")
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
    @Operation(summary = "이메일 중복 확인",description = "이메일 중복 확인합니다.")
    @ApiResponse(responseCode = "200",description = "이메일 중복 확인에 성공하였습니다.")
    public void checkDuplicateEmail(@RequestParam("email") String email) {
        userService.checkDuplicateEmail(email);
    }

    /**
     * 닉네임 중복확인
     *
     * @param nickName 닉네임
     */
    @GetMapping("stm/check-nickName")
    @Operation(summary = "닉네임 중복 확인",description = "닉네임 중복 확인합니다.")
    @ApiResponse(responseCode = "200",description = "닉네임 중복 확인에 성공하였습니다.")
    public void checkDuplicateNickName(@RequestParam("nickName") String nickName) {
        userService.checkDuplicateNickName(nickName);
    }

    /**
     * 회원가입
     *
     * @param request 회원가입할 entity
     */
    @PostMapping("stm/signup")
    @Operation(summary = "회원가입",description = "회원가입을 합니다.")
    @ApiResponse(responseCode = "200",description = "회원가입에 성공하였습니다.")
    public void signUp(@RequestBody SignupRequest request) {
        userService.save(request);
    }


}
