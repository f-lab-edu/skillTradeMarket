package com.flab.skilltrademarket.domain.review.dto.response;

import com.flab.skilltrademarket.domain.review.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Slice;

import java.util.List;
@Schema(description = "리뷰 리스트 응답")
public record ReviewListResponse(
        List<ReviewResponse> reviewResponseList,
        boolean hasNext
) {
    public static ReviewListResponse from(Slice<Review> reviews) {
        return new ReviewListResponse(
                reviews.stream()
                        .map(ReviewResponse::from)
                        .toList(), reviews.hasNext());
    }
}
