package com.flab.skilltrademarket.domain.user.response;

import com.flab.skilltrademarket.global.security.jwt.Token;

public record TokenResponse(
        String accessToken,
        Long accessTokenExpiredAt,
        String refreshToken
) {
    public static TokenResponse of(Token token) {
        return new TokenResponse(
                token.accessToken(),
                token.accessTokenExpiredAt(),
                token.refreshToken()
        );
    }
}
