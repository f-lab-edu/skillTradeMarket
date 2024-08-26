package com.flab.skilltrademarket.domain.reply.dto.request;

import com.flab.skilltrademarket.domain.reply.Reply;
import com.flab.skilltrademarket.domain.review.Review;

public record ReplyCreateRequest(
    String content
) {
    public static Reply toEntity(ReplyCreateRequest request, Review review) {
        return Reply.builder()
                .content(request.content)
                .review(review)
                .build();
    }
}
