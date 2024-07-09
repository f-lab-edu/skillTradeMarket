package com.flab.skilltrademarket.domain.term;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Table
@AttributeOverride(name = "id", column = @Column(name = "term_history_id"))
@Entity(name = "term_history")
public class TermHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "term_type", nullable = false)
    private TermType termType;

    @Column(name = "revision_date", nullable = false)
    private LocalDateTime revisionDate;


    @Column(name = "is_essential", nullable = false)
    private boolean isEssential;

    @Column(name = "is_latest_revision", nullable = false)
    private boolean isLatestRevision;

    @Builder
    public TermHistory(TermType termType, LocalDateTime revisionDate, boolean isEssential, boolean isLatestRevision) {
        this.termType = termType;
        this.revisionDate = revisionDate;
        this.isEssential = isEssential;
        this.isLatestRevision = isLatestRevision;
    }
}
