package com.flab.skilltrademarket.domain.expert.dto.response;

import com.flab.skilltrademarket.domain.review.Review;

public record PopularExpertResponse(
        String expertName,
        String skillName,
        Double rating,
        String reviewComment,
        String reviewUsername
) {
    public static PopularExpertResponse fromEntity(Review review) {
        return new PopularExpertResponse(
                review.getExpert().getUser().getNickname(),
                review.getSubCategory().getName(),
                review.getRating(),
                review.getContent(),
                review.getWriter().getNickname());
    }

}
