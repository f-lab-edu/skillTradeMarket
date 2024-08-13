package com.flab.skilltrademarket.domain.skill;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.expert.Expert;
import com.flab.skilltrademarket.domain.expert.dto.request.ExpertAddSubCatRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class ExpertSkill extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @Builder
    public ExpertSkill(Expert expert, SubCategory subCategory) {
        this.expert = expert;
        this.subCategory = subCategory;
    }

    public void addExpert(Expert expert) {
        this.expert = expert;
    }
}
