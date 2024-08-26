package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.estimate.dto.request.UserEstimateCreateRequest;
import com.flab.skilltrademarket.domain.estimate.dto.response.UserEstimateListResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.UserEstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserEstimateController {
    private final UserEstimateService userEstimateService;

    @PostMapping("/userEstimate")
    public void create(@AuthenticationUser UserDetails user, @RequestParam("subCategoryId") Long subCategoryId, @RequestBody UserEstimateCreateRequest createRequest) {
        userEstimateService.create(user.id(), subCategoryId, createRequest);
    }

    @GetMapping("/userEstimate")
    public CommonResponse<UserEstimateListResponse> findUserEstimates(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(userEstimateService.findAllByUserId(user.id()));
    }

}
