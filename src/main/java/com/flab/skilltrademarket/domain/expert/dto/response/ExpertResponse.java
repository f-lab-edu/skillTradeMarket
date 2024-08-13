package com.flab.skilltrademarket.domain.expert.dto.response;

import com.flab.skilltrademarket.domain.expert.Expert;

public record ExpertResponse(
        Long id,
        String storeName,
        String description,
        int maxDistance,
        String location,
        double rating

) {
    public static ExpertResponse from(Expert expert) {
        return new ExpertResponse(
                expert.getId(),
                expert.getStoreName(),
                expert.getStoreName(),
                expert.getMaxDistance(),
                expert.getLocation(),
                expert.getRating()
        );
    }
}
