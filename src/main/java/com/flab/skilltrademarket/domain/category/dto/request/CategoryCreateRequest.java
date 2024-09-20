package com.flab.skilltrademarket.domain.category.dto.request;

import com.flab.skilltrademarket.domain.category.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 생성 요청")
public record CategoryCreateRequest(
        @Schema(description = "카테고리명", example = "외주")
        String name,
        @Schema(description = "카테고리 설명", example = "다양한 분야의 전문가를 통해 고품질의 결과물을 제공합니다.")
        String description) {

    public static Category toEntity(CategoryCreateRequest createRequest) {
        return Category.builder()
                .name(createRequest.name)
                .description(createRequest.description)
                .build();
    }

}
