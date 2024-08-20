package com.flab.skilltrademarket.domain.estimate;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.expert.Expert;
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
public class ExpertEstimate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_estimate_id")
    private UserEstimate userEstimate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    private String description;

    private int totalCost;

    private String activityLocation;
    @Builder
    public ExpertEstimate(Expert expert, UserEstimate userEstimate, SubCategory subCategory, int totalCost,
                          String description, String activityLocation) {
        isValidCost(totalCost);
        this.expert = expert;
        this.userEstimate = userEstimate;
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
