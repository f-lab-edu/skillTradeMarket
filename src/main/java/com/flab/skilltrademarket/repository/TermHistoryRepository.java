package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.term.TermHistory;
import com.flab.skilltrademarket.domain.term.TermType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermHistoryRepository extends JpaRepository<TermHistory,Long> {

    List<TermHistory> findAllByTermTypeInAndIsLatestRevision(List<TermType> termTypes, boolean isLatestRevision);
}
