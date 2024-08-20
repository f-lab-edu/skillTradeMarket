package com.flab.skilltrademarket.domain.review.dto.response;

import com.flab.skilltrademarket.domain.reply.Reply;
import com.flab.skilltrademarket.domain.reply.dto.reponse.ReplyResponse;
import com.flab.skilltrademarket.domain.review.Review;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        String expert,
        String writer,
        String subItem,
        double rating,
        String content,
        boolean replyExisted,
        ReplyResponse reply,
        LocalDateTime updatedAt
        ) {

    public static ReviewResponse from(Review review) {
        ReplyResponse replyResponse = null;
        Reply reviewReply = review.getReply();
        boolean replyExisted = reviewReply != null;

        if (replyExisted) {
            replyResponse = new ReplyResponse(review.getExpert().getStoreName(),
                    reviewReply.getContent(),
                    reviewReply.getUpdatedAt());
        }

        return new ReviewResponse(review.getId(), review.getExpert().getStoreName(), review.getWriter().getNickname(),
                review.getSubCategory().getName(), review.getRating(), review.getContent(), replyExisted,
                replyResponse, review.getUpdatedAt());
    }
}
