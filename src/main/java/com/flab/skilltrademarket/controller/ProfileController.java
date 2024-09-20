package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidListResponse;
import com.flab.skilltrademarket.domain.user.response.ProfileResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "마이페이지", description = "마이페이지 조회, 회원역할 변경 API")
@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/stm/user/profile")
    @Operation(
            summary = "마이페이지 조회",
            description = "마이페이지를 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200",description = "마이페이지 조회에 성공하였습니다.",content = @Content(schema = @Schema(implementation = ProfileResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<ProfileResponse> getUserProfile(@AuthenticationUser UserDetails user) {
        return CommonResponse.success(profileService.getUserProfile(user.id()));
    }

    @PatchMapping("/stm/user/role")
    @Operation(
            summary = "회원 역할 변경",
            description = "회원 역할을 변경합니다.",
            responses = {
                @ApiResponse(responseCode = "200",description = "회원 역할 변경에 성공하였습니다.")
            }
    )
    public void changeRole(@AuthenticationUser UserDetails user) {
        profileService.changeRole(user.id());
    }


}
