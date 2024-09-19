package com.flab.skilltrademarket.common;

import com.flab.skilltrademarket.global.exception.ExceptionCode;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "공통 응답")
public record CommonResponse<T>(
        @Schema(description = "결과")
        String result,
        @Schema(description = "데이터")
        T data,
        @Schema(description = "에러코드" ,example = "U001")
        String errorCode,
        @Schema(description = "에러메시지", example = "에러메시지")
        String errorMessage
) {
    public static <T> CommonResponse<T> success(){
        return new CommonResponse<>("success", null, null, null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>("success", data, null, null);
    }
    public static <T> CommonResponse<T> error(String errorCode, String message){
        return new CommonResponse<>("error", null,errorCode,message);
    }
    public static <T> CommonResponse<T> error(ExceptionCode exceptionCode){
        return new CommonResponse<>("error", null, exceptionCode.getInternalMessage(), null);
    }

}
