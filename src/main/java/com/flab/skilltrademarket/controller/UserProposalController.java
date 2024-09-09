package com.flab.skilltrademarket.controller;

import  com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.proposal.dto.request.UserProposalCreateRequest;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalListResponse;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.UserProposalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@Tag(name = "요청서", description = "회원 견적 요청서 생성, 회원 ID로 견적 요청서 모두 조회, 단건 조회, 삭제 API")
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
    @Operation(summary = "회원 요청서 생성",description = "회원 요청서를 생성합니다.")
    @ApiResponse(responseCode = "200",description = "회원 요청서 생성에 성공하였습니다.")
    public void create(@AuthenticationUser UserDetails user, @RequestParam("storeId") Long storeId, @RequestBody UserProposalCreateRequest createRequest) {
        userProposalService.create(user.id(), storeId, createRequest);
    }

    /**
     * 회원 ID로 모든 견적 요청서 조회
     * @param user
     * @return
     */
    @GetMapping("/stm/userProposal")
    @Operation(summary = "회원 ID로 요청서 모두 조회",description = "회원 ID로 모든 요청서를 조회합니다.")
    @ApiResponse(responseCode = "200",description = "회원 ID로 모든 요청서 조회에 성공하였습니다.")
    public CommonResponse<UserProposalListResponse> findUserProposals(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(userProposalService.findAllByUserId(user.id()));
    }

    /**
     * 회원 요청서 ID로 단건 조회
     * @param userProposalId
     * @return
     */
    @GetMapping("/stm/userProposal/{userProposalId}")
    @Operation(summary = "회원 요청서 ID로 요청서 단건 조회",description = "회원 요청서 ID로 단건 조회합니다.")
    @ApiResponse(responseCode = "200",description = "회원 요청서 ID로 단건 조회에 성공하였습니다.")
    public CommonResponse<UserProposalResponse> findUserProposalByProposalId(@PathVariable Long userProposalId) {
        return CommonResponse.success(userProposalService.findByProposalId(userProposalId));
    }

    /**
     * 회원 요청서 ID로 삭제
     * @param user
     * @param userProposalId
     */
    @DeleteMapping("/stm/userProposal/{userProposalId}")
    @Operation(summary = "회원 요청서 삭제",description = "회원 요청서 ID로 삭제합니다.")
    @ApiResponse(responseCode = "200",description = "회원 요청서 ID로 삭제에 성공하였습니다.")
    public void deleteUserProposal(@AuthenticationUser UserDetails user, @PathVariable Long userProposalId) {
        userProposalService.deleteByProposalId(user.id(), userProposalId);
    }
}
