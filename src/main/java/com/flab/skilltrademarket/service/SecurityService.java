package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.phone.RedisService;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.jwt.Token;
import com.flab.skilltrademarket.global.security.jwt.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    private static final String TOKEN_PREFIX = "refToken:";
    private final RedisService redisService;

    private final TokenProvider tokenProvider;

    public Token createToken(String username) {
        Token token = tokenProvider.generateToken(username);

        redisService.set(TOKEN_PREFIX + username
                , token.refreshToken()
                , tokenProvider.getRefreshTokenExpiredTime()
        );
        return token;
    }

    public Token reissueToken(String token){
        try {
            String username = tokenProvider.getClaimUsername(token);

            redisService.get(TOKEN_PREFIX + username, String.class)
                    .orElseThrow(() -> new ApiException(ExceptionCode.ACCESS_DENIED));

            return createToken(username);
        } catch (ExpiredJwtException e) {
            throw new ApiException(ExceptionCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new ApiException(ExceptionCode.ACCESS_DENIED);
        }

    }
}
