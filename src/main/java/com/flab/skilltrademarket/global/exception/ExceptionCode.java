package com.flab.skilltrademarket.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    // User
    NOT_FOUND_EMAIL("U001","이메일은 필수 입력 정보입니다.","이메일은 필수 입력 정보입니다.",HttpStatus.BAD_REQUEST),
    NOT_FOUND_NICKNAME("U001","닉네임은 필수 입력 정보입니다.","닉네임은 필수 입력 정보입니다.",HttpStatus.BAD_REQUEST),
    VALIDATE_EMAIL("U001","이메일 형식이 맞지 않습니다.","잘못된 형식입니다.",HttpStatus.BAD_REQUEST),
    VALIDATE_NICKNAME("U002","닉네임 형식이 맞지 않습니다.","잘못된 형식입니다.",HttpStatus.BAD_REQUEST),
    VALIDATE_PHONE_NUM("U003","전화번호 형식이 맞지 않습니다.","잘못된 형식입니다.",HttpStatus.BAD_REQUEST),
    DUPLICATE_EMAIL("U011","중복된 이메일[%s]입니다.","사용할 수 없는 이메일입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_NICKNAME("U012","중복된 닉네임[%s]입니다.","사용할 수 없는 닉네임입니다.", HttpStatus.BAD_REQUEST),
    INVALID_PW_CONFIRM("U013", "비밀번호가 일치하지 않습니다.", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_CHECKED_ESSENTIAL_TERM("U014","필수약관에 동의해야합니다.","필수약관에 동의하지 않았습니다.",HttpStatus.BAD_REQUEST);

    private final String code;
    private final String internalMessage;
    private final String externalMessage;
    private final HttpStatus httpStatus;

}
