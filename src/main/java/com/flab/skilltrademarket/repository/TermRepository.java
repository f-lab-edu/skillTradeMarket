package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.term.Term;
import com.flab.skilltrademarket.domain.term.TermType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TermRepository extends JpaRepository<Term,Long> {

    List<Term> findAllByTermTypeInAndIsLatestRevision(List<TermType> termTypes, boolean isLatestRevision);
}
