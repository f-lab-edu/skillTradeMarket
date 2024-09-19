package com.flab.skilltrademarket.domain.phone.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "핸드폰 인증 요청")
public record PhoneVerifyRequest(
        @Schema(description = "핸드폰 번호", example = "010-1234-1234")
        String phoneNum) {

}
