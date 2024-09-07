package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.user.response.ProfileResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/stm/user/profile")
    public CommonResponse<ProfileResponse> getUserProfile(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(profileService.getUserProfile(user.id()));
    }

    @PatchMapping("/stm/user/role")
    public void changeRole(@AuthenticationUser UserDetails user) {
        profileService.changeRole(user.id());
    }


}
