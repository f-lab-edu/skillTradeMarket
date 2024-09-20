package com.flab.skilltrademarket.domain.category.dto.request;

import com.flab.skilltrademarket.domain.category.Category;
import com.flab.skilltrademarket.domain.category.SubCategory;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "하위 카테고리 생성 요청")
public record SubCategoryCreateRequest(
        @Schema(description = "메인 카테고리 Id", example = "1")
        Long mainSkillId,
        @Schema(description = "하위 카테고리명", example = "가전 제품")
        String name,
        @Schema(description = "상세 설명", example = "가전제품을 수리합니다")
        String description) {
    public static SubCategory toEntity(Category category, SubCategoryCreateRequest createRequest) {
        return SubCategory.builder()
                .category(category)
                .name(createRequest.name)
                .description(createRequest.description)
                .build();
    }
}
