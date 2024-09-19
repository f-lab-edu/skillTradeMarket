package com.flab.skilltrademarket.domain.user.dto;

import com.flab.skilltrademarket.domain.term.TermType;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.utils.ValidationUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
@Schema(description = "회원가입 요청")
public record SignupRequest(
        @Schema(description = "이메일", example = "abcd@naver.com")
        @NotBlank(message = "이메일 주소를 입력해주세요.")
        String email,
        @Schema(description = "닉네임", example = "test1234")
        @NotBlank(message = "닉네임을 입력해주세요.")
        String nickname,
        @Schema(description = "비밀번호", example = "abcd1234!!")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password,
        @Schema(description = "비밀번호 확인", example = "abcd1234!!")
        String confirmPassword,
        @Schema(description = "핸드폰 번호", example = "010-1234-1234")
        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        String phone,
        @Schema(description = "약관 동의", example = "ESSENTIAL_IS_OVER_FOURTEEN")
        @NotNull(message = "약관 동의는 필수 입니다.")
        List<TermType> termTypeList

){
    public User toEntity(String encodePassword){
        validCheck();
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(encodePassword)
                .phone(phone)
                .build();
    }


    private void validCheck() {
        ValidationUtil.validateEmail(email);
        ValidationUtil.validateNickname(nickname);
        ValidationUtil.validatePhoneNum(phone);
        ValidationUtil.confirmPassword(password,confirmPassword);
    }
}
