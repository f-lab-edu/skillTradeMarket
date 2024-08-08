package com.flab.skilltrademarket.domain.skill.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;

public record PopularSkillResponse(
        String subCategoryName,
        String requestCount,
        String imageUrl

) {
    public static PopularSkillResponse fromEntity(SubCategory subCategory) {
        return new PopularSkillResponse(subCategory.getName(), subCategory.getRequestCount().toString().concat("명 요청"), subCategory.getImageUrl());
    }
}
