package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.pay.dto.request.PayInfoRequest;
import com.flab.skilltrademarket.domain.pay.dto.response.PayApproveResponse;
import com.flab.skilltrademarket.domain.pay.dto.response.PayReadyResponse;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalListResponse;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "카카오 결제", description = "결제 기능 API")
public class PayController {

    private final PayService payService;

    @PostMapping("/stm/payment/ready")
    @Operation(
            summary = "결제 준비",
            description = "카카오 페이 결제를 시작하기 위해 결제정보를 카카오페이 서버에 전달하고 결제 고유번호와 URL을 응답 받는다.",
            responses = {
                    @ApiResponse(responseCode = "200",description = "결제 준비가 완료되었습니다",content = @Content(schema = @Schema(implementation = PayReadyResponse.class)))
            }
    )
    public PayReadyResponse readyToPay(@AuthenticationUser UserDetails user, @RequestBody PayInfoRequest payInfoRequest) {
        return payService.readyToPay(user, payInfoRequest);
    }
    @GetMapping("/payment/success")
    @Operation(
            summary = "결제 승인",
            description = "결제가 승인되었습니다.",
            responses = {
                    @ApiResponse(responseCode = "200",description = "결제 승인에 성공하였습니다.")
            }
    )
    public CommonResponse<PayApproveResponse> afterPayRequest(@RequestParam("pg_token") String pgToken, @RequestParam("userProposalId") Long userProposalId, @RequestParam("userId") Long userId) {
        return CommonResponse.success(payService.approveResponse(pgToken, userProposalId, userId));
    }

    @GetMapping("/payment/fail")
    public void fail() {
        throw new ApiException(ExceptionCode.PAYMENT_FAILED);
    }

    @GetMapping("/payment/cancel")
    public void cancel() {
        throw new ApiException(ExceptionCode.NOT_FOUND);
    }
}
