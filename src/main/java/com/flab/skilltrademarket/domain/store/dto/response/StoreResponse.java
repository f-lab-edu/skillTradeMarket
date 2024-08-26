package com.flab.skilltrademarket.domain.store.dto.response;

import com.flab.skilltrademarket.domain.store.Store;

public record StoreResponse(
        Long id,
        String storeName,
        String description,
        int maxDistance,
        String location,
        double rating

) {
    public static StoreResponse from(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getStoreName(),
                store.getStoreName(),
                store.getMaxDistance(),
                store.getLocation(),
                store.getRating()
        );
    }
}
