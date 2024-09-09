package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategoryListResponse;
import com.flab.skilltrademarket.domain.store.dto.request.StoreAddSubCatRequest;
import com.flab.skilltrademarket.domain.store.dto.request.StoreCreateRequest;
import com.flab.skilltrademarket.domain.store.dto.request.StoreUpdateRequest;
import com.flab.skilltrademarket.domain.store.dto.response.StoreResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상점", description = "상점 생성, 수정, 상점 계정 조회, 상점 삭제, 상점 서비스 추가, 상점 제공하는 서비스 조회, 상점 서비스 삭제 API")
@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    /**
     * 고수 생성
     *
     * @param user
     * @param storeCreateRequest
     */
    @PostMapping("/stm/store")
    @Operation(summary = "상점 생성",description = "상점을 생성합니다.")
    @ApiResponse(responseCode = "200",description = "상점 생성에 성공하였습니다.")
    public void create(@AuthenticationUser UserDetails user, @RequestBody StoreCreateRequest storeCreateRequest) {
        storeService.create(user, storeCreateRequest);
    }

    /**
     * 고수 수정
     *
     * @param user
     * @param storeUpdateRequest
     */
    @PatchMapping("/stm/store")
    @Operation(summary = "상점 수정",description = "상점을 수정합니다.")
    @ApiResponse(responseCode = "200",description = "상점 수정에 성공하였습니다.")
    public void update(@AuthenticationUser UserDetails user, @RequestBody StoreUpdateRequest storeUpdateRequest) {
        storeService.update(user, storeUpdateRequest);
    }

    /**
     * 고수 계정 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/stm/store/{id}")
    @Operation(summary = "상점 계정 조회",description = "상점 계정을 조회합니다.")
    @ApiResponse(responseCode = "200",description = "상점 계정 조회에 성공하였습니다.")
    public CommonResponse<StoreResponse> findOne(@PathVariable("id") Long id) {
        return CommonResponse.success(storeService.findById(id));
    }

    /**
     * 고수 삭제
     *
     * @param user
     */
    @DeleteMapping("/stm/store")
    @Operation(summary = "상점 삭제",description = "상점을 삭제합니다.")
    @ApiResponse(responseCode = "200",description = "상점 삭제에 성공하였습니다.")
    public void delete(@AuthenticationUser UserDetails user) {
        storeService.delete(user);
    }

    /**
     * 고수 서비스 추가
     *
     * @param user
     * @param request
     */
    @PostMapping("/stm/store/subCategory")
    @Operation(summary = "상점에 서비스 추가",description = "상점에 서비스를 생성합니다.")
    @ApiResponse(responseCode = "200",description = "상점 서비스 생성에 성공하였습니다.")
    public void addSubCategory(@AuthenticationUser UserDetails user, @RequestBody StoreAddSubCatRequest request) {
        storeService.addSubCategory(user, request);
    }

    /**
     * 고수 제공하는 서비스 조회
     *
     * @param user
     * @return
     */
    @GetMapping("/stm/store/subCategory")
    @Operation(summary = "상점 제공하는 서비스 조회",description = "상점이 제공하는 서비스 조회")
    @ApiResponse(responseCode = "200",description = "상점 제공하는 서비스 조회에 성공하였습니다.")
    public CommonResponse<SubCategoryListResponse> getSubCategoryById(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(storeService.getSubCategoryById(user.id()));
    }

    @DeleteMapping("/stm/store/subCategory")
    @Operation(summary = "상점 서비스 삭제",description = "상점 서비스 삭제합니다.")
    @ApiResponse(responseCode = "200",description = "상점 서비스 삭제에 성공하였습니다.")
    public void deleteExpertSkill(@AuthenticationUser UserDetails user, @RequestBody StoreAddSubCatRequest request) {
        storeService.deleteExpertSkill(user, request);
    }

}
