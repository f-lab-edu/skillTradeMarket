package com.flab.skilltrademarket.domain.store.dto.response;

import com.flab.skilltrademarket.domain.store.Store;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "스토어 응답")
public record StoreResponse(
        @Schema(description = "스토어 Id",example = "1")
        Long id,
        @Schema(description = "스토어명",example = "Astore")
        String storeName,
        @Schema(description = "스토어 상세 설명",example = "A부터Z까지 다 해주는 스토어입니다.")
        String description,
        @Schema(description = "이동 가능 거리 km", example = "10")
        int maxDistance,
        @Schema(description = "위치",example = "서울시 강남구")
        String location,
        @Schema(description = "평점",example = "5.0")
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
