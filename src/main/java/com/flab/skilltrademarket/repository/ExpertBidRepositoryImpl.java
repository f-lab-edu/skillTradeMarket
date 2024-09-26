package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidDto;
import com.flab.skilltrademarket.domain.bid.dto.response.QExpertBidDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.flab.skilltrademarket.domain.bid.QExpertBid.expertBid;
import static com.flab.skilltrademarket.domain.proposal.QUserProposal.userProposal;
import static com.flab.skilltrademarket.domain.store.QStore.store;

@Repository
@RequiredArgsConstructor
public class ExpertBidRepositoryImpl implements CustomExpertBidRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ExpertBidDto> findAllByExpertBidIdPage(Long expertBidId, Long cursorId) {
        QExpertBidDto expertBidFields = new QExpertBidDto(
                expertBid.id,
                store.id,
                store.storeName,
                store.maxDistance,
                store.location,
                store.rating,
                userProposal.id,
                userProposal.user.id,
                userProposal.subCategory.id,
                expertBid.totalCost,
                expertBid.activityLocation,
                expertBid.description,
                userProposal.detailedDescription,
                userProposal.preferredStartDate
        );
        return jpaQueryFactory.select(expertBidFields)
                .from(expertBid)
                .where(ltExpertBidId(cursorId))
                .orderBy(expertBid.id.desc())
                .limit(11)      // 다음 페이지 유무 확인
                .fetch();
    }

    private BooleanExpression ltExpertBidId(Long cursorId) {
        return cursorId != null ? expertBid.id.lt(cursorId) : null;
    }
}
