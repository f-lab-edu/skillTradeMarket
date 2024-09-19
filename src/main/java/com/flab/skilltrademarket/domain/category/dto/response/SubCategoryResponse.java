package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "하위 카테고리 응답")
public record SubCategoryResponse(
        @Schema(description = "하위카테고리 Id", example = "1")
        Long id,
        @Schema(description = "상위카테고리명", example = "가전")
        String mainCategory,
        @Schema(description = "카테고리 명", example = "가전제품")
        String name,
        @Schema(description = "상세 설명", example = "가전을 고칩니다")
        String description
) {

    public static SubCategoryResponse from(SubCategory subCategory) {
        return new SubCategoryResponse(subCategory.getId(), subCategory.getCategory().getName(), subCategory.getName(), subCategory.getDescription());
    }
}
