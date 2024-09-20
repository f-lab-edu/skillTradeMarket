package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidListResponse;
import com.flab.skilltrademarket.domain.category.dto.request.SubCategoryCreateRequest;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategoryListResponse;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategoryResponse;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategorySliceListResponse;
import com.flab.skilltrademarket.service.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
@Tag(name = "하위 카테고리", description = "하위 카테고리 생성, 단건 조회, 모두 조회, 하위카테고리 이름으로 상위카테고리 조회  API")
@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping("/stm/sub/create")
    @Operation(
            summary = "하위 카테고리 생성",
            description = "하위 카테고리를 생성합니다.",
            responses = {
                @ApiResponse(responseCode = "200",description = "하위 카테고리 생성에 성공하였습니다.")
            }
    )
    public void create(@RequestBody SubCategoryCreateRequest createRequest) {
        subCategoryService.create(createRequest);
    }

    @GetMapping("/stm/sub/{id}")
    @Operation(
            summary = "하위카테고리 단건 조회",
            description = "하위카테고리 단건 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200",description = "하위 카테고리 단건 조회에 성공하였습니다.",content = @Content(schema = @Schema(implementation = SubCategoryResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<SubCategoryResponse> getOne(@Parameter(description = "하위카테고리 Id",example = "1") @PathVariable("id") Long id) {
        return CommonResponse.success(subCategoryService.findById(id));
    }

    @GetMapping("/stm/sub")
    @Operation(
            summary = "하위 카테고리 전체 조회",
            description = "하위 카테고리를 모두 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200",description = "하위 카테고리 모두 조회에 성공하였습니다.",content = @Content(schema = @Schema(implementation = SubCategoryResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<SubCategoryListResponse> getList() {
        return CommonResponse.success(subCategoryService.findAll());
    }

    @GetMapping("/stm/sub/categoryName")
    @Operation(
            summary = "하위 카테고리 이름으로 상위 카테고리 조회",
            description = "하위 카테고리로 상위 카테고리를 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200",description = "하위 카테고리 이름으로 상위 카테고리 조회에 성공하였습니다.",content = @Content(schema = @Schema(implementation = SubCategorySliceListResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<SubCategorySliceListResponse> findSubCategoryByMainCategory(@Parameter(description = "카테고리명",example = "가전")@RequestParam("categoryName") String categoryName,
                                                                                      @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return CommonResponse.success(subCategoryService.findAllByMainCategory(categoryName, pageable));
    }
}
