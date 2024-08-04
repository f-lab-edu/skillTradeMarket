package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.category.Category;
import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.category.dto.request.SubCategoryCreateRequest;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.CategoryRepository;
import com.flab.skilltrademarket.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    @Transactional
    public void create(SubCategoryCreateRequest createRequest) {
        Category category = categoryRepository.findById(createRequest.mainSkillId())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SKILL));

        dupCheckName(createRequest.name());

        SubCategory subCategory = SubCategoryCreateRequest.toEntity(category, createRequest);

        subCategoryRepository.save(subCategory);
    }

    private void dupCheckName(String name) {
        if(subCategoryRepository.existsByName(name)){
            throw new ApiException(ExceptionCode.DUP_SKILL_NAME,name);
        }
    }
}
