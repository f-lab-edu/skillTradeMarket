package com.flab.skilltrademarket.global.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
@Getter
public class ApiException extends RuntimeException{
    private final ExceptionCode code;

    public ApiException(ExceptionCode code, Consumer<String> logFunction) {
        super(code.getExternalMessage());
        this.code = code;
        logMessage(logFunction);
    }

    private void logMessage(Consumer<String> logFunction) {
        String message = String.format("Class Name: %s, Message: %s",logFunction.getClass().getPackageName(), getMessage());
        logFunction.accept(message);

    }

    public ApiException(ExceptionCode code, String... args){
        super(String.format(code.getInternalMessage(), args));
        this.code = code;
    }
    public ApiException(ExceptionCode code) {
        super(code.getExternalMessage());
        this.code = code;
    }
}
