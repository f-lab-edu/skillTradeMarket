package com.flab.skilltrademarket.domain.phone.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "핸드폰 인증 확인 요청")
public record PhoneConfirmRequest(
        @Schema(description = "핸드폰 번호", example = "010-1234-1234")
        String phoneNum,
        @Schema(description = "인증 번호", example = "201535")
        int code) {

}
