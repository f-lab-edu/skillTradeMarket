package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.proposal.dto.request.UserProposalCreateRequest;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalListResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.UserProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserProposalController {
    private final UserProposalService userProposalService;

    @PostMapping("/userProposal")
    public void create(@AuthenticationUser UserDetails user, @RequestParam("subCategoryId") Long subCategoryId, @RequestBody UserProposalCreateRequest createRequest) {
        userProposalService.create(user.id(), subCategoryId, createRequest);
    }

    @GetMapping("/userProposal")
    public CommonResponse<UserProposalListResponse> findUserProposals(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(userProposalService.findAllByUserId(user.id()));
    }

}
