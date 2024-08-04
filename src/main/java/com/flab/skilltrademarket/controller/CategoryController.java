package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.category.dto.request.CategoryCreateRequest;
import com.flab.skilltrademarket.domain.category.dto.request.CategoryUpdateRequest;
import com.flab.skilltrademarket.domain.category.dto.response.CategoryListResponse;
import com.flab.skilltrademarket.domain.category.dto.response.CategoryResponse;
import com.flab.skilltrademarket.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/stm/create")
    public void create(@RequestBody CategoryCreateRequest createRequest) {
        categoryService.create(createRequest);
    }

    @GetMapping("/stm/{id}")
    CommonResponse<CategoryResponse> findOne(@PathVariable("id") Long id) {
        CategoryResponse skillResponse = categoryService.findOneById(id);
        return CommonResponse.success(skillResponse);
    }

    @GetMapping("/stm")
    CommonResponse<CategoryListResponse> findAll() {
        return CommonResponse.success(categoryService.findAll());
    }

    @PatchMapping("/stm/{id}")
    CommonResponse<CategoryResponse> update(@PathVariable("id") Long id, @RequestBody CategoryUpdateRequest updateRequest) {
        return CommonResponse.success(categoryService.update(id, updateRequest));
    }

    @DeleteMapping("/stm/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }
}
