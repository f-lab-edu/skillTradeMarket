package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.category.dto.request.CategoryCreateRequest;
import com.flab.skilltrademarket.domain.category.dto.request.CategoryUpdateRequest;
import com.flab.skilltrademarket.domain.category.dto.response.CategoryListResponse;
import com.flab.skilltrademarket.domain.category.dto.response.CategoryResponse;
import com.flab.skilltrademarket.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@Tag(name = "카테고리", description = "카테고리 생성, 단건 조회, 모두 조회, 수정, 삭제 API")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/stm/create")
    @Operation(summary = "카테고리 생성",description = "카테고리를 생성합니다.")
    @ApiResponse(responseCode = "200",description = "카테고리 생성에 성공하였습니다.")
    public void create(@RequestBody CategoryCreateRequest createRequest) {
        categoryService.create(createRequest);
    }

    @GetMapping("/stm/{id}")
    @Operation(summary = "카테고리 단건 조회",description = "카테고리를 단건 조회합니다.")
    @ApiResponse(responseCode = "200",description = "카테고리 단건 조회에 성공하였습니다.")
    CommonResponse<CategoryResponse> findOne(@PathVariable("id") Long id) {
        return CommonResponse.success(categoryService.findOneById(id));
    }

    @GetMapping("/stm")
    @Operation(summary = "카테고리 모두 조회",description = "카테고리를 모두 조회 합니다.")
    @ApiResponse(responseCode = "200",description = "카테고리 모두 조회에 성공하였습니다.")
    CommonResponse<CategoryListResponse> findAll() {
        return CommonResponse.success(categoryService.findAll());
    }

    @PatchMapping("/stm/{id}")
    @Operation(summary = "카테고리 수정",description = "카테고리를 수정합니다.")
    @ApiResponse(responseCode = "200",description = "카테고리 수정에 성공하였습니다.")
    CommonResponse<CategoryResponse> update(@PathVariable("id") Long id, @RequestBody CategoryUpdateRequest updateRequest) {
        return CommonResponse.success(categoryService.update(id, updateRequest));
    }

    @DeleteMapping("/stm/{id}")
    @Operation(summary = "카테고리 삭제",description = "카테고리를 삭제합니다.")
    @ApiResponse(responseCode = "200",description = "카테고리 삭제에 성공하였습니다.")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }
}
