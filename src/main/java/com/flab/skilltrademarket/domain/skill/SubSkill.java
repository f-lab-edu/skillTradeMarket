package com.flab.skilltrademarket.domain.skill;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class SubSkill extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_skill_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MainSkill mainSkill;

    private String name;

    private String description;

    @Builder
    public SubSkill(MainSkill mainSkill, String name, String description) {
        this.mainSkill = mainSkill;
        this.name = name;
        this.description = description;
    }

}
