package com.flab.skilltrademarket.domain.estimate;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.expert.Expert;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserEstimate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime preferredStartDate;

    @Column(length = 500)
    private String detailedDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @Builder
    public UserEstimate(User user, SubCategory subCategory, String location, String detailedDescription,LocalDateTime strDate) {
        isValidDate(strDate);
        this.user = user;
        this.subCategory = subCategory;
        this.location = location;
        this.detailedDescription = detailedDescription;
        this.preferredStartDate = strDate;
    }


    private void isValidDate(LocalDateTime strDate) {
        if (LocalDateTime.now().isAfter(strDate)) {
            throw new ApiException(ExceptionCode.ACCESS_DENIED);
        }
    }
}
