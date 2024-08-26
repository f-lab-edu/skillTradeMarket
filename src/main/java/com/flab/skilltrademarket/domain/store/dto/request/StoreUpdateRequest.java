package com.flab.skilltrademarket.domain.store.dto.request;

import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.utils.ValidationUtil;

public record StoreUpdateRequest(
        String storeName,
        String description,
        Integer maxDistance,
        String location
) {
    public static Store update(StoreUpdateRequest request) {
        request.validCheck();
        return Store.builder()
                .storeName(request.storeName())
                .description(request.description())
                .maxDistance(request.maxDistance())
                .location(request.location())
                .build();
    }

    private void validCheck() {
        ValidationUtil.validateStoreName(storeName);
    }
}
