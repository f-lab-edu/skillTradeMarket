package com.flab.skilltrademarket.domain.review.dto.response;

import com.flab.skilltrademarket.domain.reply.Reply;
import com.flab.skilltrademarket.domain.reply.dto.response.ReplyResponse;
import com.flab.skilltrademarket.domain.review.Review;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "리뷰 응답")
public record ReviewResponse(
        @Schema(description = "리뷰 Id", example = "1")
        Long id,
        @Schema(description = "스토어명", example = "Astore")
        String store,
        @Schema(description = "작성자", example = "test1234")
        String writer,
        @Schema(description = "하위카테고리명", example = "가전 제품")
        String subItem,
        @Schema(description = "평점", example = "4.0")
        double rating,
        @Schema(description = "내용", example = "서비스에 아주 만족했습니다.")
        String content,
        @Schema(description = "댓글 여부")
        boolean replyExisted,
        @Schema(description = "댓글 응답")
        ReplyResponse reply,
        @Schema(description = "수정 시간", example = "2024-09-30T13:11:20")
        LocalDateTime updatedAt
        ) {

    public static ReviewResponse from(Review review) {
        ReplyResponse replyResponse = null;
        Reply reviewReply = review.getReply();
        boolean replyExisted = reviewReply != null;

        if (replyExisted) {
            replyResponse = new ReplyResponse(review.getStore().getStoreName(),
                    reviewReply.getContent(),
                    reviewReply.getUpdatedAt());
        }

        return new ReviewResponse(review.getId(), review.getStore().getStoreName(), review.getWriter().getNickname(),
                review.getSubCategory().getName(), review.getRating(), review.getContent(), replyExisted,
                replyResponse, review.getUpdatedAt());
    }
}
