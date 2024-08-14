package com.flab.skilltrademarket.domain.expert.dto.request;

import com.flab.skilltrademarket.domain.expert.Expert;

public record ExpertUpdateRequest(
        String storeName,
        String description,
        Integer maxDistance,
        String location
) {
    public static Expert update(ExpertUpdateRequest request) {
        return Expert.builder()
                .storeName(request.storeName())
                .description(request.description())
                .maxDistance(request.maxDistance())
                .location(request.location())
                .build();
    }
}