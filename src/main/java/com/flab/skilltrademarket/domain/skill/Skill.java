package com.flab.skilltrademarket.domain.skill;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.expert.Expert;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Skill extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert;


    private String name;
    private String description;


}
