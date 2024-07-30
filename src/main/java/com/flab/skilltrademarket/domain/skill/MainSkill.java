package com.flab.skilltrademarket.domain.skill;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class MainSkill extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;


    @Builder
    public MainSkill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void update(MainSkill mainSkill) {
        this.name = mainSkill.getName();
        this.description = mainSkill.getDescription();
    }

}
