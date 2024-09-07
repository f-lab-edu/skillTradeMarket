package com.flab.skilltrademarket.controller;

import  com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.proposal.dto.request.UserProposalCreateRequest;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalListResponse;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.UserProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserProposalController {
    private final UserProposalService userProposalService;

    /**
     * 회원 견적 요청서 생성
     * @param user
     * @param storeId
     * @param createRequest
     */
    @PostMapping("/stm/userProposal")
    public void create(@AuthenticationUser UserDetails user, @RequestParam("storeId") Long storeId, @RequestBody UserProposalCreateRequest createRequest) {
        userProposalService.create(user.id(), storeId, createRequest);
    }

    /**
     * 회원 ID로 모든 견적 요청서 조회
     * @param user
     * @return
     */
    @GetMapping("/stm/userProposal")
    public CommonResponse<UserProposalListResponse> findUserProposals(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(userProposalService.findAllByUserId(user.id()));
    }

    /**
     * 회원 요청서 ID로 단건 조회
     * @param userProposalId
     * @return
     */
    @GetMapping("/stm/userProposal/{userProposalId}")
    public CommonResponse<UserProposalResponse> findUserProposalByProposalId(@PathVariable Long userProposalId) {
        return CommonResponse.success(userProposalService.findByProposalId(userProposalId));
    }

    /**
     * 회원 요청서 ID로 삭제
     * @param user
     * @param userProposalId
     */
    @DeleteMapping("/stm/userProposal/{userProposalId}")
    public void deleteUserProposal(@AuthenticationUser UserDetails user, @PathVariable Long userProposalId) {
        userProposalService.deleteByProposalId(user.id(), userProposalId);
    }
}
