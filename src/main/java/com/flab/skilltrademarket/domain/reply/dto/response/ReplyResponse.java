package com.flab.skilltrademarket.domain.reply.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "댓글 응답")
public record ReplyResponse(
        @Schema(description = "스토어 생성 Id", example = "test123")
        String expert,
        @Schema(description = "내용", example = "소중한 리뷰 감사합니다.")
        String content,
        @Schema(description = "수정 시간", example = "2024-09-30T13:11:20")
        LocalDateTime updatedAt
) {
}
