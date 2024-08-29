package com.flab.skilltrademarket.domain.store.dto.response;

import com.flab.skilltrademarket.domain.review.Review;

public record PopularStoreResponse(
        String storeName,
        String skillName,
        Double rating,
        String reviewComment,
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
