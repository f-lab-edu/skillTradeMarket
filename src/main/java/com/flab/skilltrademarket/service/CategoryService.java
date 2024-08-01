package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.category.Category;
import com.flab.skilltrademarket.domain.category.dto.request.CategoryCreateRequest;
import com.flab.skilltrademarket.domain.category.dto.request.CategoryUpdateRequest;
import com.flab.skilltrademarket.domain.category.dto.response.CategoryListResponse;
import com.flab.skilltrademarket.domain.category.dto.response.CategoryResponse;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Transactional
    public void create(CategoryCreateRequest createRequest) {
        checkDupName(createRequest.name());

        Category category = CategoryCreateRequest.toEntity(createRequest);
        categoryRepository.save(category);
    }



    public CategoryResponse findOneById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SKILL));
        return CategoryResponse.from(category);
    }


    public CategoryListResponse findAll() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryListResponse.from(categories);
    }


    @Transactional
    public CategoryResponse update(Long id, CategoryUpdateRequest updateRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SKILL));

        checkDupName(updateRequest.name());
        Category updateCategory = CategoryUpdateRequest.toEntity(updateRequest);
        category.update(updateCategory);

        return CategoryResponse.from(category);
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SKILL));

        categoryRepository.delete(category);
    }

    private void checkDupName(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new ApiException(ExceptionCode.DUP_SKILL_NAME, name);
        }
    }
}
