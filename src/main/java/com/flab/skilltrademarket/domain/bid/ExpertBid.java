package com.flab.skilltrademarket.domain.bid;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ExpertBid extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_proposal_id")
    private UserProposal userProposal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    private String description;

    private int totalCost;

    private String activityLocation;
    @Builder
    public ExpertBid(Store store, UserProposal userProposal, SubCategory subCategory, int totalCost,
                     String description, String activityLocation) {
        isValidCost(totalCost);
        this.store = store;
        this.userProposal = userProposal;
        this.subCategory = subCategory;
        this.totalCost = totalCost;
        this.activityLocation = activityLocation;
        this.description = description;
    }

    private void isValidCost(int totalCost) {
        if (totalCost <= 0) {
            throw new ApiException(ExceptionCode.COST_MUST_OVER_ZERO);
        }
    }
}
