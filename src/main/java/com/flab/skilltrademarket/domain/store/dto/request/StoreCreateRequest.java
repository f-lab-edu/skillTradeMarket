package com.flab.skilltrademarket.domain.store.dto.request;

import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.utils.ValidationUtil;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "스토어 생성 요청")
public record StoreCreateRequest(
        @Schema(description = "스토어명", example = "Astore")
        String storeName,
        @Schema(description = "스토어 상세 설명", example = "타일을 전문적으로 합니다.")
        String description,
        @Schema(description = "최대 이동 거리 km", example = "10")
        Integer maxDistance,
        @Schema(description = "위치", example = "서울시 강남구")
        String location
) {
    public Store toEntity(User user) {
        validCheck();
        return Store.builder()
                .user(user)
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
