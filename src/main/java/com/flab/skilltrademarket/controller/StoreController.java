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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    /**
     * 고수 생성
     * @param user
     * @param storeCreateRequest
     */
    @PostMapping("/stm/store")
    public void create(@AuthenticationUser UserDetails user, @RequestBody StoreCreateRequest storeCreateRequest) {
        storeService.create(user, storeCreateRequest);
    }

    /**
     * 고수 수정
     * @param user
     * @param storeUpdateRequest
     */
    @PatchMapping("/stm/store")
    public void update(@AuthenticationUser UserDetails user, @RequestBody StoreUpdateRequest storeUpdateRequest) {
        storeService.update(user, storeUpdateRequest);
    }

    /**
     * 고수 계정 조회
     * @param id
     * @return
     */
    @GetMapping("/stm/store/{id}")
    public CommonResponse<StoreResponse> findOne(@PathVariable("id") Long id) {
        return CommonResponse.success(storeService.findById(id));
    }

    /**
     * 고수 삭제
     * @param user
     */
    @DeleteMapping("/stm/store")
    public void delete(@AuthenticationUser UserDetails user) {
        storeService.delete(user);
    }

    /**
     * 고수 서비스 추가
     * @param user
     * @param request
     */
    @PostMapping("/stm/store/subCategory")
    public void addSubCategory(@AuthenticationUser UserDetails user, @RequestBody StoreAddSubCatRequest request) {
        storeService.addSubCategory(user, request);
    }

    /**
     * 고수 제공하는 서비스 조회
     * @param user
     * @return
     */
    @GetMapping("/stm/store/subCategory")
    public CommonResponse<SubCategoryListResponse> getSubCategoryById(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(storeService.getSubCategoryById(user.id()));
    }

    @DeleteMapping("/stm/store/subCategory")
    public void deleteExpertSkill(@AuthenticationUser UserDetails user, @RequestBody StoreAddSubCatRequest request) {
        storeService.deleteExpertSkill(user, request);
    }

}
