package com.flab.skilltrademarket.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "로그인 요청")
public record LoginRequest(
        @Schema(description = "사용자 이메일", example = "abcd@naver.com")
        String email,
        @Schema(description = "비밀번호",example = "abcd1234!")
        String password
) {
}
