package com.flab.skilltrademarket.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    DUPLICATE_EMAIL("중복된 이메일입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_NICKNAME("중복된 닉네임입니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

}
