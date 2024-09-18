package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.pay.dto.request.OrderRequest;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.PaymentService;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/stm/validation/{impUid}")
    public IamportResponse<Payment> validateIamport(@PathVariable("impUid") String impUid) {
        return paymentService.validateIamport(impUid);
    }

    @PostMapping("/stm/order")
    public CommonResponse<String> processOrder(@AuthenticationUser UserDetails userDetails, @RequestBody OrderRequest orderRequest) {
        return paymentService.processOrder(userDetails, orderRequest);
    }

    @PostMapping("/stm/cancel/{impUid}")
    public IamportResponse<Payment> cancelOrder(@PathVariable("impUid") String impUid) {
        return paymentService.cancelPayment(impUid);
    }
}
