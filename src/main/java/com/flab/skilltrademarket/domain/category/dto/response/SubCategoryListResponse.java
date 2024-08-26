package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.skill.ExpertSkill;

import java.util.List;
import java.util.stream.Collectors;

public record SubCategoryListResponse(
        List<SubCategoryResponse> subCategoryResponses
) {
    public static SubCategoryListResponse from(List<SubCategory> subCategories) {
        return new SubCategoryListResponse(subCategories.stream()
                .map(SubCategoryResponse::from)
                .toList());
    }

    public static SubCategoryListResponse from(Store store) {
        List<SubCategory> subCategories = store.getExpertSkills().stream()
                .map(ExpertSkill::getSubCategory)
                .collect(Collectors.toList());

        return new SubCategoryListResponse(subCategories.stream()
                .map(SubCategoryResponse::from)
                .toList());

    }
}
