package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.HomeResponse;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stm/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public CommonResponse<HomeResponse> getHome() {
        return CommonResponse.success(homeService.getHome());
    }
}
