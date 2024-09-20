package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 응답")
public record CategoryResponse(
        @Schema(description = "카테고리 Id", example = "1")
        Long id,
        @Schema(description = "카테고리명", example = "외주")
        String name,
        @Schema(description = "카테고리 상세명", example = "다양한 분야의 전문가를 통해 고품질의 결과물을 제공합니다.")
        String description) {

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }
}
