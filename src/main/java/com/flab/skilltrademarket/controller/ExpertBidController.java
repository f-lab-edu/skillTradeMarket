package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.bid.dto.request.ExpertBidCreateRequest;
import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidListResponse;
import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidPageResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ExpertBidService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "고수 응답서", description = "고수 응답서 생성 , 모든 응답서 조회, 검색으로 응답서 조회 API")
@RestController
@RequiredArgsConstructor
public class ExpertBidController {
    private final ExpertBidService expertBidService;

    /**
     * 응답서 생성
     *
     * @param user
     * @param createRequest
     */
    @PostMapping("/stm/expertBid")
    @Operation(
            summary = "응답서 생성",
            description = "응답서를 생성합니다.",
            responses = {
                @ApiResponse(responseCode = "200",description = "응답서 생성에 성공하였습니다.")
            }
    )
    public void create(@AuthenticationUser UserDetails user, @RequestBody ExpertBidCreateRequest createRequest) {
        expertBidService.create(user, createRequest);
    }

    /**
     * 모든 응답서 조회
     *
     * @return
     */
    @GetMapping("/stm/expertBid")
    @Operation(
            summary = "응답서 모두 조회",
            description = "모든 응답서를 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "응답서 조회에 성공하였습니다.", content = @Content(schema = @Schema(implementation = ExpertBidListResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<ExpertBidListResponse> findExpertBids() {
        return CommonResponse.success(expertBidService.findExpertBids());
    }

    @GetMapping("/stm/expertBid/search/{expertBidId}/{cursorId}")
    @Operation(
            summary = "응답서 검색",
            description = "응답서를 검색합니다",
            responses = {
                @ApiResponse(responseCode = "200",description = "응답서 검색에 성공하였습니다.",content = @Content(schema = @Schema(implementation = ExpertBidPageResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<ExpertBidPageResponse> searchExpertBids(@AuthenticationUser UserDetails user, @PathVariable("expertBidId") Long expertBidId, @PathVariable("cursorId") Long cursorId) {
        if (cursorId < 0) {
            return CommonResponse.success(new ExpertBidPageResponse());
        }
        return CommonResponse.success(expertBidService.searchExpertBids(expertBidId, cursorId));
    }


}
