package com.flab.skilltrademarket.global.security.jwt;

public interface TokenProvider {
    Token generateToken(String username);

    String getClaimUsername(String token);

    Long getRefreshTokenExpiredTime();
}
