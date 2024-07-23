package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.user.dto.LoginRequest;
import com.flab.skilltrademarket.domain.user.response.TokenResponse;
import com.flab.skilltrademarket.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/stm/login")
    TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}
