package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.category.dto.request.SubCategoryCreateRequest;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategoryListResponse;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategoryResponse;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategorySliceListResponse;
import com.flab.skilltrademarket.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping("/stm/sub/create")
    public void create(@RequestBody SubCategoryCreateRequest createRequest) {
        subCategoryService.create(createRequest);
    }

    @GetMapping("/stm/sub/{id}")
    public CommonResponse<SubCategoryResponse> getOne(@PathVariable("id") Long id) {
        return CommonResponse.success(subCategoryService.findById(id));
    }

    @GetMapping("/stm/sub")
    public CommonResponse<SubCategoryListResponse> getList() {
        return CommonResponse.success(subCategoryService.findAll());
    }

    @GetMapping("/stm/sub/categoryName")
    public CommonResponse<SubCategorySliceListResponse> findSubCategoryByMainCategory(@RequestParam("categoryName") String categoryName, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return CommonResponse.success(subCategoryService.findAllByMainCategory(categoryName, pageable));
    }
}
