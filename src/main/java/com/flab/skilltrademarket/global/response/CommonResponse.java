package com.flab.skilltrademarket.global.response;

public record CommonResponse<T>(
        String result,
        T data,
        String errorCode,
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

}
