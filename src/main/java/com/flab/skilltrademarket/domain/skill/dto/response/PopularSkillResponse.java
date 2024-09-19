package com.flab.skilltrademarket.domain.skill.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인기 서비스 응답")
public record PopularSkillResponse(
        @Schema(description = "하위 카테고리 명",example = "가전 제품")
        String subCategoryName,
        @Schema(description = "요청 수",example = "10")
        String requestCount,
        @Schema(description = "이미지 url",example = "\\img.png")
        String imageUrl

) {
    public static PopularSkillResponse fromEntity(SubCategory subCategory) {
        return new PopularSkillResponse(subCategory.getName(), subCategory.getRequestCount().toString().concat("명 요청"), subCategory.getImageUrl());
    }
}
