package com.flab.skilltrademarket.domain.category.dto.request;

import com.flab.skilltrademarket.domain.category.Category;
import com.flab.skilltrademarket.domain.category.SubCategory;

public record SubCategoryCreateRequest(Long mainSkillId,
                                       String name,
                                       String description) {
    public static SubCategory toEntity(Category category, SubCategoryCreateRequest createRequest) {
        return SubCategory.builder()
                .category(category)
                .name(createRequest.name)
                .description(createRequest.description)
                .build();
    }
}
