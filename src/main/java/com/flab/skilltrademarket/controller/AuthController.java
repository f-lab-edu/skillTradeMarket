package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.user.dto.LoginRequest;
import com.flab.skilltrademarket.domain.user.dto.LogoutRequest;
import com.flab.skilltrademarket.domain.user.dto.ReissueRequest;
import com.flab.skilltrademarket.domain.user.response.TokenResponse;
import com.flab.skilltrademarket.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "로그인")
    TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/stm/logout")
    @Operation(summary = "로그아웃")
    public void logout(@RequestBody LogoutRequest logoutRequest) {
        loginService.logout(logoutRequest);
    }

    @PostMapping("/stm/reissue")
    @Operation(summary = "토큰 재발급")
    TokenResponse reissue(@RequestBody ReissueRequest reissueRequest) {
        return loginService.reissue(reissueRequest);
    }


}
