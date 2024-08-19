package com.flab.skilltrademarket.domain.expert.dto.request;

import com.flab.skilltrademarket.domain.expert.Expert;
import com.flab.skilltrademarket.domain.user.User;

public record ExpertCreateRequest(

    String storeName,
    String description,
    Integer maxDistance,
    String location
) {
    public Expert toEntity(User user) {
        return Expert.builder()
                .user(user)
                .storeName(storeName)
                .description(description)
                .maxDistance(maxDistance)
                .location(location)
                .build();
    }
}
