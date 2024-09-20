package com.flab.skilltrademarket.domain.category.dto.request;

import com.flab.skilltrademarket.domain.category.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 수정 요청")
public record CategoryUpdateRequest(
        @Schema(description = "카테고리명", example = "외주")
        String name,
        @Schema(description = "카테고리 상세설명",example = "다양한 분야의 전문가를 통해 고품질의 결과물을 제공합니다.")
        String description) {

    public static Category toEntity(CategoryUpdateRequest updateRequest) {
        return Category.builder()
                .name(updateRequest.name)
                .description(updateRequest.description)
                .build();
    }
}
