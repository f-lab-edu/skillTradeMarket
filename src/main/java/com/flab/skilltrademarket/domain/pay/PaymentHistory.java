package com.flab.skilltrademarket.domain.pay;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class PaymentHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long userProposalId;
    private Long expertBidId;
    private String itemName;
    private Integer totalCost;


    @Builder
    public PaymentHistory(Long userProposalId, Long expertBidId, String itemName, Integer totalCost) {
        this.userProposalId = userProposalId;
        this.expertBidId = expertBidId;
        this.itemName = itemName;
        this.totalCost = totalCost;
    }



}
