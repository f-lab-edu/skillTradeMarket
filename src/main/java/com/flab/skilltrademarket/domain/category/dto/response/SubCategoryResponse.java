package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;

public record SubCategoryResponse(
        Long id,
        String mainCategory,
        String name,
        String description
) {

    public static SubCategoryResponse from(SubCategory subCategory) {
        return new SubCategoryResponse(subCategory.getId(), subCategory.getCategory().getName(), subCategory.getName(), subCategory.getDescription());
    }
}
