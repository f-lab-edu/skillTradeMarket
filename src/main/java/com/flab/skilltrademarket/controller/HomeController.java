package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.HomeResponse;
import com.flab.skilltrademarket.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "홈", description = "홈 화면")
@RestController
@RequestMapping("/stm/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    @Operation(
            summary = "홈 화면 조회",
            description = "홈 화면을 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200",description = "홈 화면 조회에 성공하였습니다.",content = @Content(schema = @Schema(implementation = HomeResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<HomeResponse> getHome() {
        return CommonResponse.success(homeService.getHome());
    }
}
