package com.flab.skilltrademarket.domain.category.dto.request;

import com.flab.skilltrademarket.domain.category.Category;

public record CategoryUpdateRequest(String name,
                                    String description) {

    public static Category toEntity(CategoryUpdateRequest updateRequest) {
        return Category.builder()
                .name(updateRequest.name)
                .description(updateRequest.description)
                .build();
    }
}
