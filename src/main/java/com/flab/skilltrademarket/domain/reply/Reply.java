package com.flab.skilltrademarket.domain.reply;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.review.Review;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String content;

    @OneToOne(mappedBy = "reply", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Review review;
    @Builder
    public Reply(Review review,String content) {
        this.content = content;
        review.addReply(this);
    }

    public void validateWriter(Long writer, Review review) {
        long expertId = review.getExpert().getId();

        if (writer != expertId) {
            throw new ApiException(ExceptionCode.UNSUPPORTED_REPLIER);
        }
    }
}
