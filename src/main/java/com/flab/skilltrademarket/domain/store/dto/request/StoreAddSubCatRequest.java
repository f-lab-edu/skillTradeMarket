package com.flab.skilltrademarket.domain.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상점 하위 카테고리 추가")
public record StoreAddSubCatRequest(
        @Schema(description = "하위 카테고리명", example = "가전 제품")
        String subCatName
) {
}
