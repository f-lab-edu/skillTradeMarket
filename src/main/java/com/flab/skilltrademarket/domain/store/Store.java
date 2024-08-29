package com.flab.skilltrademarket.domain.store;

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
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
//
//    @OneToOne(fetch = LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
    private Long userId;
    @OneToMany(mappedBy = "store", cascade = ALL, orphanRemoval = true)
    private List<ExpertSkill> expertSkills = new ArrayList<>();

    private String storeName;

    private String description;

    private int maxDistance;

    private String location;

    private double rating;
    private int reviewCount;

    @Builder
    public Store(Long userId, String storeName, String description, int maxDistance, String location, double rating, int reviewCount) {
        this.userId = userId;
        this.storeName = storeName;
        this.description = description;
        this.maxDistance = maxDistance;
        this.location = location;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }

    public void update(Store store) {
        this.storeName = store.getStoreName();
        this.description = store.getDescription();
        this.maxDistance = store.getMaxDistance();
        this.location = store.getLocation();
        this.rating = store.getRating();
        this.reviewCount = store.getReviewCount();
    }

    public void addExpertSkill(ExpertSkill skill) {
        skill.addExpert(this);
        this.getExpertSkills().add(skill);
    }

    public void deleteExpertSkill(ExpertSkill skill) {
        this.getExpertSkills().remove(skill);
    }

    public void calculateRating(double newRating) {
        validateRating(newRating);

        // 기존 리뷰 개수에 새로운 평균 계산
        this.rating = calculateNewRating(this.rating, newRating, this.reviewCount);

        // 리뷰 개수를 증가
        this.reviewCount++;
    }

    private void validateRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
    }

    private double calculateNewRating(double currentRating, double newRating, int currentReviewCount) {
        if (currentReviewCount <= 0) {
            return roundToNearestTenth(newRating);
        }

        // 새로운 평점을 계산
        double updatedRating = (currentRating * currentReviewCount + newRating) / (currentReviewCount + 1);
        return roundToNearestTenth(updatedRating);
    }

    private double roundToNearestTenth(double value) {
        return Math.round(value * 10) / 10.0;
    }

}
