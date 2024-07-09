package com.flab.skilltrademarket.domain.user.dto;

import com.flab.skilltrademarket.domain.term.TermType;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import jakarta.validation.constraints.*;

import java.util.List;
public record SignupRequest(

        @NotBlank(message = "이메일 주소를 입력해주세요.")
        @Email(message = "올바른 이메일 주소를 입력해주세요.")
        String email,
        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
        String nickname,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
        String password,

        String confirmPassword,
        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        @Pattern(regexp = "(01[01])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
        String phone,

        @NotNull(message = "약관 동의는 필수 입니다.")
        List<TermType> termTypeList

){
    public User toEntity(String encodePassword){
        if (!password.equals(confirmPassword)) {
            throw new ApiException(ExceptionCode.INVALID_PW_CONFIRM);
        }
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(encodePassword)
                .phone(phone)
                .build();
    }
}
