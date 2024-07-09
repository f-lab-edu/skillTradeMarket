package com.flab.skilltrademarket.global.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Getter
public class ApiException extends RuntimeException{
    private final ExceptionCode code;
    public ApiException(ExceptionCode code) {
        super(code.getExternalMessage());
        this.code = code;
    }
}
