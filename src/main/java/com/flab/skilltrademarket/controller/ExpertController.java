package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategoryListResponse;
import com.flab.skilltrademarket.domain.expert.dto.request.ExpertAddSubCatRequest;
import com.flab.skilltrademarket.domain.expert.dto.request.ExpertCreateRequest;
import com.flab.skilltrademarket.domain.expert.dto.request.ExpertUpdateRequest;
import com.flab.skilltrademarket.domain.expert.dto.response.ExpertResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ExpertController {
    private final ExpertService expertService;

    /**
     * 고수 생성
     * @param user
     * @param expertCreateRequest
     */
    @PostMapping("/expert")
    public void create(@AuthenticationUser UserDetails user, @RequestBody ExpertCreateRequest expertCreateRequest) {
        expertService.create(user, expertCreateRequest);
    }

    /**
     * 고수 수정
     * @param user
     * @param expertUpdateRequest
     */
    @PatchMapping("/expert")
    public void update(@AuthenticationUser UserDetails user, @RequestBody ExpertUpdateRequest expertUpdateRequest) {
        expertService.update(user, expertUpdateRequest);
    }

    /**
     * 고수 계정 조회
     * @param id
     * @return
     */
    @GetMapping("/expert/{id}")
    public CommonResponse<ExpertResponse> findOne(@PathVariable("id") Long id) {
        return CommonResponse.success(expertService.findById(id));
    }

    /**
     * 고수 삭제
     * @param user
     */
    @DeleteMapping("/expert")
    public void delete(@AuthenticationUser UserDetails user) {
        expertService.delete(user);
    }

    /**
     * 고수 서비스 추가
     * @param user
     * @param request
     */
    @PostMapping("/expert/subCategory")
    public void addSubCategory(@AuthenticationUser UserDetails user, @RequestBody ExpertAddSubCatRequest request) {
        expertService.addSubCategory(user, request);
    }

    /**
     * 고수 제공하는 서비스 조회
     * @param user
     * @return
     */
    @GetMapping("/expert/subCategory")
    public CommonResponse<SubCategoryListResponse> getSubCategoryById(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(expertService.getSubCategoryById(user.id()));
    }

    @DeleteMapping("/expert/subCategory")
    public void deleteExpertSkill(@AuthenticationUser UserDetails user, @RequestBody ExpertAddSubCatRequest request) {
        expertService.deleteExpertSkill(user, request);
    }

}
