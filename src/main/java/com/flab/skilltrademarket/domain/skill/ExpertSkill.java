package com.flab.skilltrademarket.domain.skill;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.store.Store;
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
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder
    public ExpertSkill(Store store, SubCategory subCategory) {
        this.store = store;
        this.subCategory = subCategory;
    }

    public void addExpert(Store store) {
        this.store = store;
    }
}
