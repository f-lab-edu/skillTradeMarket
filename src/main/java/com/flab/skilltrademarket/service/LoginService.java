package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.user.dto.LoginRequest;
import com.flab.skilltrademarket.domain.user.dto.LogoutRequest;
import com.flab.skilltrademarket.domain.user.dto.ReissueRequest;
import com.flab.skilltrademarket.domain.user.dto.UserInfo;
import com.flab.skilltrademarket.domain.user.response.TokenResponse;
import com.flab.skilltrademarket.global.security.encryption.PasswordEncoder;
import com.flab.skilltrademarket.global.security.jwt.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final SecurityService securityService;
    public TokenResponse login(LoginRequest loginRequest) {
        String encodedPassword = passwordEncoder.encode(loginRequest.password());
        UserInfo user = userService.getLoginUser(loginRequest.email(), encodedPassword);
        Token token = securityService.createToken(user.email());
        return TokenResponse.of(token);
    }

    public TokenResponse reissue(ReissueRequest reissueRequest) {
        Token token = securityService.reissueToken(reissueRequest.refreshToken());
        return TokenResponse.of(token);
    }

    public void logout(LogoutRequest logoutRequest) {
        securityService.logout(logoutRequest.accessToken());
    }
}
