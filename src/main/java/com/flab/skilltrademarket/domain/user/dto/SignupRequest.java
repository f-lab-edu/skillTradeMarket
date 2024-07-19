package com.flab.skilltrademarket.domain.user.dto;

import com.flab.skilltrademarket.domain.term.TermType;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.utils.ValidationUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
public record SignupRequest(

        @NotBlank(message = "이메일 주소를 입력해주세요.")
        String email,
        @NotBlank(message = "닉네임을 입력해주세요.")
        String nickname,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password,

        String confirmPassword,
        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        String phone,

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
