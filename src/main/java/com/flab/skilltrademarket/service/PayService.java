package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.pay.PaymentHistory;
import com.flab.skilltrademarket.domain.pay.dto.request.PayInfoRequest;
import com.flab.skilltrademarket.domain.pay.dto.response.PayApproveResponse;
import com.flab.skilltrademarket.domain.pay.dto.response.PayReadyResponse;
import com.flab.skilltrademarket.domain.proposal.Status;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.repository.ExpertBidRepository;
import com.flab.skilltrademarket.repository.PaymentHistoryRepository;
import com.flab.skilltrademarket.repository.UserProposalRepository;
import com.flab.skilltrademarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PayService {
    private PayReadyResponse payReadyResponse;
    private final UserRepository userRepository;
    private final UserProposalRepository userProposalRepository;
    private final ExpertBidRepository expertBidRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    @Value("${kakaopay.cid}")
    private String cid;
    @Value("${kakaopay.key}")
    private String adminKey;
    @Value("${kakaopay.url.approval}")
    private String approvalUrl;
    @Value("${kakaopay.url.cancel}")
    private String cancelUrl;
    @Value("${kakaopay.url.fail}")
    private String failUrl;

    public PayReadyResponse readyToPay(UserDetails user, PayInfoRequest payInfoRequest) {
        // 카카오페이 API로 정보 전송
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(createReadyPayParameters(user,payInfoRequest), this.getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        try {
            payReadyResponse = restTemplate.postForObject(
                    "https://kapi.kakao.com/v1/payment/ready",
                    requestEntity,
                    PayReadyResponse.class
            );
        } catch (Exception e) {
            throw new ApiException(ExceptionCode.PAYMENT_READY_FAILED);
        }
        return payReadyResponse;
    }
    public PayApproveResponse approveResponse(String pgToken, Long userProposalId,Long userId) {

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(createApproveParameters(pgToken,userId), this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        PayApproveResponse approveResponse;
        try {
            approveResponse = restTemplate.postForObject(
                    "https://kapi.kakao.com/v1/payment/approve",
                    requestEntity,
                    PayApproveResponse.class);

        } catch (Exception e) {
            throw new ApiException(ExceptionCode.PAYMENT_FAILED);
        }
        payProcess(userId, userProposalId);
        return approveResponse;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String auth = "KakaoAK " + adminKey;
        headers.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization", auth);
        return headers;
    }

    private MultiValueMap<String, String> createReadyPayParameters(UserDetails user, PayInfoRequest payInfoRequest) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id","stm:"+user.id());
        parameters.add("partner_user_id","stm");
        parameters.add("item_name", payInfoRequest.itemName());
        parameters.add("quantity", "1");
        parameters.add("total_amount", String.valueOf(payInfoRequest.totalCost()));
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", approvalUrl+"?userProposalId=" + payInfoRequest.proposalId() + "&userId=" + user.id());
        parameters.add("cancel_url", cancelUrl);
        parameters.add("fail_url", failUrl);

        return parameters;
    }

    private MultiValueMap<String, String> createApproveParameters(String pgToken, Long userId) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid",cid);
        parameters.add("tid", payReadyResponse.tid());
        parameters.add("partner_order_id","stm:"+userId);
        parameters.add("partner_user_id","stm");
        parameters.add("pg_token",pgToken);
        return parameters;
    }

    private void payProcess(Long userId, Long userProposalId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        UserProposal userProposal = userProposalRepository.findById(userProposalId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_USER_PROPOSAL));

        ExpertBid expertBid = expertBidRepository.findByUserProposalId(userProposal.getId())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_BID));

        PaymentHistory paymentHistory = PayInfoRequest.toEntity(userProposal, expertBid);
        userProposal.updateStatus(Status.FINISHED);
        paymentHistoryRepository.save(paymentHistory);
    }
}
