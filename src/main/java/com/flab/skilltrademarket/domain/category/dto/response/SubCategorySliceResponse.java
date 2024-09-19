package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "하위 카테고리 페이징 응답")
public record SubCategorySliceResponse(
        @Schema(description = "하위카테고리 Id", example = "1")
        Long id,
        @Schema(description = "하위 카테고리명", example = "가전 제품")
        String name,
        @Schema(description = "상세 설명", example = "가전 제품을 수리합니다.")
        String description
) {
    public static SubCategorySliceResponse from(SubCategory subCategory) {
        return new SubCategorySliceResponse(subCategory.getId(), subCategory.getName(), subCategory.getDescription());
    }
}
