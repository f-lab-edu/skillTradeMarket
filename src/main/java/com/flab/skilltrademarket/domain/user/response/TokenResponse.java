package com.flab.skilltrademarket.domain.user.response;

import com.flab.skilltrademarket.global.security.jwt.Token;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "토큰 응답")
public record TokenResponse(
        @Schema(description = "접근 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNkQG5hdmVyLmNvbSIsImlhdCI6MTcyNjcwNDUwNCwiZXhwIjoxNzI2NzA4MTA0LCJ1c2VybmFtZSI6ImFiY2RAbmF2ZXIuY29tIn0.yQcnxMy9zxmmAj-UdEfrj2Pe3Ixc7lfvsZjyI8S6pWRzeziZ2GAtpOsmKhdr0hlwSAmjtPJkrE5WLtOzRovfww")
        String accessToken,
        @Schema(description = "토큰 만료 시간",example = "3600")
        Long accessTokenExpiredAt,
        @Schema(description = "재발급 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNkQG5hdmVyLmNvbSIsImlhdCI6MTcyNjcwNDUwNCwiZXhwIjoxNzI2NzA4MTA0LCJ1c2VybmFtZSI6ImFiY2RAbmF2ZXIuY29tIn0.yQcnxMy9zxmmAj-UdEfrj2Pe3Ixc7lfvsZjyI8S6pWRzeziZ2GAtpOsmKhdr0hlwSAmjtPJkrE5WLtOzRovfww")
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
