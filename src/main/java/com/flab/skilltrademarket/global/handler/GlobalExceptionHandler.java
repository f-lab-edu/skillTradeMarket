package com.flab.skilltrademarket.global.handler;

import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.response.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<CommonResponse<Void>> handleCustomApiException(ApiException e,HttpServletRequest request) {
        ExceptionCode error = e.getCode();
        printLog(request,error);
        CommonResponse<Void> response = CommonResponse.error(error.getCode(), error.getExternalMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    private void printLog(HttpServletRequest request, ExceptionCode e) {
        log.error("code={} | internal_msg={} | status={} | method={} | uri={}",
                e.getCode(), e.getInternalMessage(), e.getHttpStatus().value(), request.getMethod(),
                request.getRequestURI());
    }


}
