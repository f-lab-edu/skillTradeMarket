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
        return categoryRepository.findById(id)
                .map(CategoryResponse::from)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_CAT));
    }


    public CategoryListResponse findAll() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryListResponse.from(categories);
    }


    @Transactional
    public CategoryResponse update(Long id, CategoryUpdateRequest updateRequest) {
        return categoryRepository.findById(id)
                .map(category -> {
                    checkDupName(updateRequest.name());
                    category.update(CategoryUpdateRequest.toEntity(updateRequest));
                    return CategoryResponse.from(category);
                })
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_CAT));
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_CAT));

        categoryRepository.delete(category);
    }

    private void checkDupName(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new ApiException(ExceptionCode.DUP_CAT_NAME, name);
        }
    }
}
