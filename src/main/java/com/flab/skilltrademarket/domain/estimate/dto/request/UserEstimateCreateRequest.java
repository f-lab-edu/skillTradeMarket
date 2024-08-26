package com.flab.skilltrademarket.domain.estimate.dto.request;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.estimate.UserEstimate;
import com.flab.skilltrademarket.domain.user.User;

import java.time.LocalDateTime;

public record UserEstimateCreateRequest(
        String location,
        String detailedDescription,
        LocalDateTime startDate
) {
    public static UserEstimate toEntity(User user, SubCategory subCategory,
                                                     UserEstimateCreateRequest createRequest) {
        return UserEstimate.builder()
                .user(user)
                .subCategory(subCategory)
                .location(createRequest.location)
                .detailedDescription(createRequest.detailedDescription)
                .strDate(createRequest.startDate)
                .build();
    }
}
