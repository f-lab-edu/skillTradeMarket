package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;

public record SubCategorySliceResponse(
        Long id,
        String name,
        String description
) {
    public static SubCategorySliceResponse from(SubCategory subCategory) {
        return new SubCategorySliceResponse(subCategory.getId(), subCategory.getName(), subCategory.getDescription());
    }
}
