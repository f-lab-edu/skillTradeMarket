package com.flab.skilltrademarket.global.handler;

import com.flab.skilltrademarket.global.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<String> handleCustomApiException(ApiException e) {
        log.error("[ApiException] Message = {}", e.getMessage());
        return new ResponseEntity<>(e.getCode().getMessage(), e.getCode().getHttpStatus());
    }
}
