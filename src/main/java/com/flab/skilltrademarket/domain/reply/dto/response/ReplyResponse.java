package com.flab.skilltrademarket.domain.reply.dto.response;

import java.time.LocalDateTime;

public record ReplyResponse(
        String expert,
        String content,
        LocalDateTime updatedAt
) {
}
