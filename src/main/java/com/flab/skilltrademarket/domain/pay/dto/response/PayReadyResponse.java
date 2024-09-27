package com.flab.skilltrademarket.domain.pay.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카카오페이 응답")
public record PayReadyResponse(
        @Schema(description = "결제 고유번호",example = "T1234567890123456789")
        String tid,
        @Schema(description = "리다이렉트 url",example = "https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/info")
        String next_redirect_pc_url,
        @Schema(description = "결제 준비 요청 시간",example = "2024-09-30T21:21:21")
        String created_at
) {
}
