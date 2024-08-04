package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.Category;

import java.util.List;

public record CategoryListResponse(List<CategoryResponse> skillList) {

    public static CategoryListResponse from(List<Category> categoryList) {
        return new CategoryListResponse(categoryList.stream()
                .map(CategoryResponse::from)
                .toList());
    }
}
