package com.flab.skilltrademarket.global.security.jwt;

public record Token(
        String accessToken,
        Long accessTokenExpiredAt,
        String refreshToken
) {
}
