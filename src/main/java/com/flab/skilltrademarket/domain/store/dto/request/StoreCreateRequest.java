package com.flab.skilltrademarket.domain.store.dto.request;

import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.utils.ValidationUtil;

public record StoreCreateRequest(

    String storeName,
    String description,
    Integer maxDistance,
    String location
) {
    public Store toEntity(Long userId) {
        validCheck();
        return Store.builder()
                .userId(userId)
                .storeName(storeName)
                .description(description)
                .maxDistance(maxDistance)
                .location(location)
                .build();
    }

    private void validCheck() {
        ValidationUtil.validateStoreName(storeName);
    }
}
