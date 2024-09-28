package com.flab.skilltrademarket.domain.pay;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
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
