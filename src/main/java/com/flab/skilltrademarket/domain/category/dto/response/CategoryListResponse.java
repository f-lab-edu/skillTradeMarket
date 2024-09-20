package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "카테고리 리스트 응답")
public record CategoryListResponse(
        @Schema(description = "카테고리 리스트")
        List<CategoryResponse> skillList) {

    public static CategoryListResponse from(List<Category> categoryList) {
        return new CategoryListResponse(categoryList.stream()
                .map(CategoryResponse::from)
                .toList());
    }
}
