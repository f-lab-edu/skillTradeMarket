package com.flab.skilltrademarket.domain.estimate.dto.response;

import com.flab.skilltrademarket.domain.estimate.UserEstimate;

import java.time.LocalDateTime;

public record UserEstimateResponse(
        Long id,
        Long userId,
        Long subCategoryId,
        String location,
        String detailedDescription,
        LocalDateTime startDate
) {
    public static UserEstimateResponse from(UserEstimate userEstimate) {
        return new UserEstimateResponse(userEstimate.getId(),
                userEstimate.getUser().getId(),
                userEstimate.getSubCategory().getId(),
                userEstimate.getLocation(),
                userEstimate.getDetailedDescription(),
                userEstimate.getPreferredStartDate());
    }
}

