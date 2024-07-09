package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.term.TermAgree;
import com.flab.skilltrademarket.domain.term.TermHistory;
import com.flab.skilltrademarket.domain.term.TermType;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.TermAgreeRepository;
import com.flab.skilltrademarket.repository.TermHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Stream;

import static com.flab.skilltrademarket.domain.term.TermType.CHECK_ESSENTIAL_LIST;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TermService {

    private final TermAgreeRepository termAgreeRepository;
    private final TermHistoryRepository termHistoryRepository;
    @Transactional
    public List<TermAgree> saveSignupTerm(Long userId, List<TermType> termTypes) {
        List<TermType> termAgreeList = Stream.of(CHECK_ESSENTIAL_LIST, termTypes)
                .flatMap(List::stream).toList();
        if (ObjectUtils.isEmpty(termTypes)|| !termTypes.containsAll(termAgreeList)) {
            throw new ApiException(ExceptionCode.NOT_CHECKED_ESSENTIAL_TERM);
        }
        List<TermHistory> termHistories = termHistoryRepository.findAllByTermTypeInAndIsLatestRevision(termAgreeList, true);
        if (ObjectUtils.isEmpty(termHistories)) {
            return null;
        }
        List<TermAgree> saveTerms = termHistories.stream().map(i -> TermAgree.of(userId, i)).toList();
        return termAgreeRepository.saveAll(saveTerms);
    }
}
