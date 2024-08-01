package com.flab.skilltrademarket.domain.category.dto.request;

import com.flab.skilltrademarket.domain.category.Category;

public record CategoryCreateRequest(String name,
                                    String description) {

    public static Category toEntity(CategoryCreateRequest createRequest) {
        return Category.builder()
                .name(createRequest.name)
                .description(createRequest.description)
                .build();
    }

}
