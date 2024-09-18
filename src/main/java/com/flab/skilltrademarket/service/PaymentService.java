package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.pay.Order;
import com.flab.skilltrademarket.domain.pay.dto.request.OrderRequest;
import com.flab.skilltrademarket.domain.proposal.Status;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.repository.OrderRepository;
import com.flab.skilltrademarket.repository.UserProposalRepository;
import com.flab.skilltrademarket.repository.UserRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final IamportClient iamportClient;
    private final UserProposalRepository userProposalRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    public IamportResponse<Payment> validateIamport(String impUid) {
        try {
            IamportResponse<Payment> payment = iamportClient.paymentByImpUid(impUid);
            log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse());
            return payment;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Transactional
    public CommonResponse<String> processOrder(UserDetails userDetails, OrderRequest orderRequest) {
        userRepository.findById(userDetails.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));

        UserProposal userProposal = userProposalRepository.findById(orderRequest.userProposalId())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_USER_PROPOSAL));
        if (!userProposal.getStatus().equals(Status.PROCEEDING)) {
            throw new ApiException(ExceptionCode.USER_AUTH_PHONE_NOT_VERIFY);
        }
        Order order = orderRequest.toEntity(userProposal);
        try {
            orderRepository.save(order);
            return CommonResponse.success("주문 정보가 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            cancelPayment(orderRequest.impUid());
        }
        return CommonResponse.error("401", "주문 정보 저장에 실패하였습니다.");
    }

    public IamportResponse<Payment> cancelPayment(String impUid) {
        try {
            CancelData cancelData = new CancelData(impUid, true);
            IamportResponse<Payment> payment = iamportClient.cancelPaymentByImpUid(cancelData);
            return payment;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
