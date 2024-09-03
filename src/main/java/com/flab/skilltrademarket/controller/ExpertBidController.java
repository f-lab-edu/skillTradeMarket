package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.bid.dto.request.ExpertBidCreateRequest;
import com.flab.skilltrademarket.domain.bid.dto.request.ExpertBidSearchCondition;
import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidListResponse;
import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidSliceListResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ExpertBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExpertBidController {
    private final ExpertBidService expertBidService;

    /**
     * 응답서 생성
     * @param user
     * @param createRequest
     */
    @PostMapping("/expertBid")
    public void create(@AuthenticationUser UserDetails user, @RequestBody ExpertBidCreateRequest createRequest) {
        expertBidService.create(user, createRequest);

    }

    /**
     * 모든 응답서 조회
     * @return
     */
    @GetMapping("/expertBid")
    public CommonResponse<ExpertBidListResponse> findExpertBids() {
        return CommonResponse.success(expertBidService.findExpertBids());
    }

    @GetMapping("/expertBid/searchAll")
    public CommonResponse<ExpertBidSliceListResponse> searchExpertBidsByAllConditions(@AuthenticationUser UserDetails user,  ExpertBidSearchCondition condition, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return CommonResponse.success(expertBidService.searchExpertBidsByCondition(user.id(), condition, pageable));
    }




}
