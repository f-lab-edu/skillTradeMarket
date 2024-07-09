package com.flab.skilltrademarket.domain.term;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table
@NoArgsConstructor(access = PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "term_agree_id"))
public class TermAgree extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TermType termType;

    @Column(name = "term_history_id", nullable = false)
    private Long termHistoryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Builder
    public TermAgree(TermType termType, Long termHistoryId, Long userId) {
        this.termType = termType;
        this.termHistoryId = termHistoryId;
        this.userId = userId;
    }

    public static TermAgree of(Long userId, TermHistory termHistory) {
        return TermAgree.builder()
                .termType(termHistory.getTermType())
                .termHistoryId(termHistory.getId())
                .userId(userId)
                .build();
    }


}
