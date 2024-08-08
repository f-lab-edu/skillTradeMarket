package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;

import java.util.List;

public record SubCategoryListResponse(
        List<SubCategoryResponse> subCategoryResponses
) {
    public static SubCategoryListResponse from(List<SubCategory> subCategories) {
        return new SubCategoryListResponse(subCategories.stream()
                .map(SubCategoryResponse::from)
                .toList());
    }
}
