package com.flab.skilltrademarket.global.security.jwt;

import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider implements TokenProvider{
    private static final int MILLISECONDS_TO_SECONDS = 1000;
    private static final String TOKEN_CLAIM_KEY = "username";

    private final SecretKey secretKey;
    private final Long accessTokenExpiredTime;
    private final Long refreshTokenExpiredTime;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-validate-in-seconds}") Long accessTokenExpiredTime,
            @Value("${jwt.refresh-token-validate-in-seconds}") Long refreshTokenExpiredTime
    ) {
        this.secretKey = getSecretKey(secret);
        this.accessTokenExpiredTime = accessTokenExpiredTime;
        this.refreshTokenExpiredTime = refreshTokenExpiredTime;
    }
    /**
     * 사용자 이름으로 JWT 액세스 토큰과 리프레시 토큰 생성
     *
     * @param username 사용자 이름
     * @return 생성된 액세스 토큰과 리프레시 토큰
     * @throws ApiException username이 null 또는 빈 값인 경우 예외 발생
     */
    @Override
    public Token generateToken(String username) {
        if (!StringUtils.hasText(username)) {
            throw new ApiException(ExceptionCode.COMMON_SYSTEM_ERROR);
        }
        String accessToken = createAccessToken(username);
        String refreshToken = createRefreshToken(username);
        return new Token(accessToken, this.accessTokenExpiredTime, refreshToken);
    }
    @Override
    public Long getRefreshTokenExpiredTime() {
        return this.refreshTokenExpiredTime * MILLISECONDS_TO_SECONDS;
    }

    /**
     * JWT 토큰에서 사용자 이름 추출
     *
     * @param token JWT 토큰
     * @return 사용자 이름
     * @throws ExpiredJwtException 토큰이 만료된 경우 예외 발생
     * @throws UnsupportedJwtException 지원되지 않는 토큰 형식인 경우 예외 발생
     * @throws MalformedJwtException 토큰이 잘못된 형식인 경우 예외 발생
     * @throws ApiException 토큰에서 사용자 아이디를 추출할 수 없는 경우 예외 발생
     */
    @Override
    public String getClaimUsername(String token) {
        Claims claims = getClaims(token);
        String username = claims.get(TOKEN_CLAIM_KEY, String.class);
        if (!StringUtils.hasText(username)) {
            throw new ApiException(ExceptionCode.COMMON_SYSTEM_ERROR);
        }
        return username;
    }

    /**
     * 비밀 문자열을 통해 HMAC SHA 알고리즘에 사용할 SecretKey 생성
     *
     * @param secret 비밀 문자열
     * @return 생성된 SecretKey
     */
    private SecretKey getSecretKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    /**
     * 사용자 이름을 포함하는 JWT 액세스 토큰 생성
     *
     * @param username 사용자 이름
     * @return 생성된 JWT 액세스 토큰 문자열
     */
    private String createAccessToken(String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + this.accessTokenExpiredTime * MILLISECONDS_TO_SECONDS);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expireDate)
                .claim(TOKEN_CLAIM_KEY, username)
                .signWith(this.secretKey)
                .compact();
    }
    /**
     * 리프레시 토큰 생성
     *
     * @return 생성된 리프레시 토큰 문자열
     */
    private String createRefreshToken(String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + this.refreshTokenExpiredTime * MILLISECONDS_TO_SECONDS);

        return Jwts.builder()
                .issuedAt(now)
                .expiration(expireDate)
                .claim(TOKEN_CLAIM_KEY, username)
                .signWith(this.secretKey)
                .compact();
    }

    /**
     * JWT 토큰에서 클레임 정보 추출
     *
     * @param token JWT 토큰
     * @return 추출된 클레임 정보
     */
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
