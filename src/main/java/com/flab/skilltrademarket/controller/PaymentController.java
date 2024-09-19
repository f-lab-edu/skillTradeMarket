package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.pay.dto.request.OrderRequest;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.PaymentService;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "결제", description = "결제 주문, 취소 API")
@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/stm/validation/{impUid}")
    @Operation(
            summary = "결제 정보 검증",
            description = "imp_uid를 통해 아임포트 결제 정보를 검증합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "결제 정보 검증에 성공하였습니다."),
                    @ApiResponse(responseCode = "404", description = "결제 정보를 찾을 수 없습니다.")
            }
    )
    public IamportResponse<Payment> validateIamport(@Parameter(description = "결제정보 Id", example = "1")@PathVariable("impUid") String impUid) {
        return paymentService.validateIamport(impUid);
    }

    @PostMapping("/stm/order")
    @Operation(
            summary = "주문 처리",
            description = "주문 정보를 받아 결제를 요청하고, 주문 정보를 저장합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "결제 요청 및 주문 정보 저장에 성공하였습니다.")
            }
    )

    public CommonResponse<String> processOrder(@AuthenticationUser UserDetails userDetails, @RequestBody OrderRequest orderRequest) {
        return paymentService.processOrder(userDetails, orderRequest);
    }

    @PostMapping("/stm/cancel/{impUid}")
    @Operation(
            summary = "결제 취소",
            description = "imp_uid를 통해 아임포트 결제를 취소합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "결제 취소에 성공하였습니다."),
                    @ApiResponse(responseCode = "404", description = "결제 정보를 찾을 수 없습니다.")
            }
    )
    public IamportResponse<Payment> cancelOrder(@PathVariable("impUid") String impUid) {
        return paymentService.cancelPayment(impUid);
    }
}
