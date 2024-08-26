package com.flab.skilltrademarket.domain.review.dto.response;

import com.flab.skilltrademarket.domain.review.Review;
import org.springframework.data.domain.Slice;

import java.util.List;

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
