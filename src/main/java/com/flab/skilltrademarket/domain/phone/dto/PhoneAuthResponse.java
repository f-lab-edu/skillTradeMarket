package com.flab.skilltrademarket.domain.phone.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "핸드폰 인증 응답")
public record PhoneAuthResponse(
        @Schema(description = "인증번호",example = "215645")
        int code) {

    public static PhoneAuthResponse from(int code){
        return new PhoneAuthResponse(code);
    }
}
