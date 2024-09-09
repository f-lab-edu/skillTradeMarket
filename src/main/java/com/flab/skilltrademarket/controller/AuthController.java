package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.user.dto.LoginRequest;
import com.flab.skilltrademarket.domain.user.dto.LogoutRequest;
import com.flab.skilltrademarket.domain.user.dto.ReissueRequest;
import com.flab.skilltrademarket.domain.user.response.TokenResponse;
import com.flab.skilltrademarket.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "인증", description = "로그인/로그아웃/토큰 재발급 API")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final LoginService loginService;

    @PostMapping("/stm/login")
    @Operation(summary = "로그인",description = "로그인을 합니다.")
    @ApiResponse(responseCode = "200",description = "로그인에 성공하였습니다.")
    TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/stm/logout")
    @Operation(summary = "로그아웃",description = "로그아웃을 합니다.")
    @ApiResponse(responseCode = "200",description = "로그아웃에 성공하였습니다.")
    public void logout(@RequestBody LogoutRequest logoutRequest) {
        loginService.logout(logoutRequest);
    }

    @PostMapping("/stm/reissue")
    @Operation(summary = "토큰 재발급",description = "토큰을 재발급 합니다.")
    @ApiResponse(responseCode = "200",description = "토큰을 재발급하였습니다.")
    TokenResponse reissue(@RequestBody ReissueRequest reissueRequest) {
        return loginService.reissue(reissueRequest);
    }


}
