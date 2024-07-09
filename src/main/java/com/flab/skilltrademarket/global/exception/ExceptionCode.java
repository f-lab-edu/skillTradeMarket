package com.flab.skilltrademarket.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    // User
    DUPLICATE_EMAIL("U001","중복된 이메일입니다.","사용할 수 없는 이메일입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_NICKNAME("U002","중복된 닉네임입니다.","사용할 수 없는 닉네임입니다.", HttpStatus.BAD_REQUEST),
    INVALID_PW_CONFIRM("U003", "비밀번호가 일치하지 않습니다.", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_CHECKED_ESSENTIAL_TERM("U004","필수약관에 동의해야합니다.","필수약관에 동의하지 않았습니다.",HttpStatus.BAD_REQUEST);

    private final String code;
    private final String internalMessage;
    private final String externalMessage;
    private final HttpStatus httpStatus;

}
