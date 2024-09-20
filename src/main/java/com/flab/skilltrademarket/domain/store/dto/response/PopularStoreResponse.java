package com.flab.skilltrademarket.domain.store.dto.response;

import com.flab.skilltrademarket.domain.review.Review;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인기 스토어 응답")
public record PopularStoreResponse(
        @Schema(description = "스토어명",example = "Astore")
        String storeName,
        @Schema(description = "서비스 명",example = "가전 제품")
        String skillName,
        @Schema(description = "평점",example = "5.0")
        Double rating,
        @Schema(description = "리뷰 댓글",example = "서비스에 만족합니다.")
        String reviewComment,
        @Schema(description = "리뷰 작성자",example = "test123")
        String reviewUsername
) {
    public static PopularStoreResponse fromEntity(Review review) {
        return new PopularStoreResponse(
                review.getStore().getStoreName(),
                review.getSubCategory().getName(),
                review.getRating(),
                review.getContent(),
                review.getWriter().getNickname());
    }

}
