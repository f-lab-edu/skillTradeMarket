package com.flab.skilltrademarket.repository;
import static com.flab.skilltrademarket.domain.bid.QExpertBid.*;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.bid.dto.request.ExpertBidSearchCondition;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExpertBidRepositoryImpl implements CustomExpertBidRepository{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Slice<ExpertBid> findExpertBidsByAllCondition(ExpertBidSearchCondition searchCondition, Pageable pageable) {
        List<ExpertBid> results = jpaQueryFactory.selectFrom(expertBid)
                .where(eqUserId(searchCondition.userId()),
                        ltExpertBidId(searchCondition.expertBidId()))
                .orderBy(expertBid.createdAt.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return checkLastPage(pageable, results);
    }

    private Slice<ExpertBid> checkLastPage(Pageable pageable, List<ExpertBid> results) {
        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }


    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? expertBid.userProposal.user.id.eq(userId) : null;
    }

    private BooleanExpression ltExpertBidId(Long expertBidId) {
        return expertBidId == null ? null : expertBid.id.lt(expertBidId);
    }
}
