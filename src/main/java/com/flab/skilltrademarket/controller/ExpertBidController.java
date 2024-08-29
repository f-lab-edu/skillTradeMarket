package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.bid.dto.request.ExpertBidCreateRequest;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ExpertBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExpertBidController {
    private final ExpertBidService expertBidService;

    @PostMapping
    public void create(@AuthenticationUser UserDetails user, @RequestBody ExpertBidCreateRequest createRequest) {
        expertBidService.create(user, createRequest);

    }
}
