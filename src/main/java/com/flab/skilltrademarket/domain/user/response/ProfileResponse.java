package com.flab.skilltrademarket.domain.user.response;

import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.domain.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "마이페이지 응답")
public record ProfileResponse(
        @Schema(description = "이메일", example = "abcd@naver.com")
        String email,
        @Schema(description = "닉네임", example = "test1234")
        String nickName,
        @Schema(description = "핸드폰 번호", example = "010-1234-1234")
        String phone,
        @Schema(description = "역할", example = "USER", defaultValue = "USER")
        UserRole role
) {
    public static ProfileResponse from(User user) {
        return new ProfileResponse(user.getEmail(), user.getNickname(), user.getPhone(), user.getUserRole());
    }
}
