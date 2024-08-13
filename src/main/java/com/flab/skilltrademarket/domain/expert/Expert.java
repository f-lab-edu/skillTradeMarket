package com.flab.skilltrademarket.domain.expert;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.skill.ExpertSkill;
import com.flab.skilltrademarket.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Expert extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "expert", cascade = ALL, orphanRemoval = true)
    private List<ExpertSkill> expertSkills = new ArrayList<>();

    private String storeName;

    private String description;

    private int maxDistance;

    private String location;

    private double rating;

    @Builder
    public Expert(User user, String storeName, String description, int maxDistance, String location, double rating) {
        this.user = user;
        this.storeName = storeName;
        this.description = description;
        this.maxDistance = maxDistance;
        this.location = location;
        this.rating = rating;
    }

    public void update(Expert expert) {
        this.storeName = expert.getStoreName();
        this.description = expert.getDescription();
        this.maxDistance = expert.getMaxDistance();
        this.location = expert.getLocation();
        this.rating = expert.getRating();
    }

    public void addExpertSkill(ExpertSkill skill) {
        skill.addExpert(this);
        this.getExpertSkills().add(skill);
    }

    public void deleteExpertSkill(ExpertSkill skill) {
        this.getExpertSkills().remove(skill);
    }
}
