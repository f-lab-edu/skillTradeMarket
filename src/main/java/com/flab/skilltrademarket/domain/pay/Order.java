package com.flab.skilltrademarket.domain.pay;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
@Table(name="orders")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_proposal_id")
    private UserProposal userProposal;

    private String productName;
    private Integer price;
    private String impUid;
    private String merchantUid;

    @Builder
    public Order(UserProposal userProposal, String productName, Integer price, String impUid, String merchantUid) {
        this.userProposal = userProposal;
        this.productName = productName;
        this.price = price;
        this.impUid = impUid;
        this.merchantUid = merchantUid;
    }

}
