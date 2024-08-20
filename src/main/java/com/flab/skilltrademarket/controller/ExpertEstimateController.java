package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.estimate.dto.request.ExpertEstimateCreateRequest;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ExpertEstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExpertEstimateController {
    private final ExpertEstimateService expertEstimateService;

    @PostMapping
    public void create(@AuthenticationUser UserDetails user, @RequestBody ExpertEstimateCreateRequest createRequest) {
        expertEstimateService.create(user, createRequest);

    }
}
