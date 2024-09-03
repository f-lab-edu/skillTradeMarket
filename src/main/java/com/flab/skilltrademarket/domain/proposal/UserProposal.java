package com.flab.skilltrademarket.domain.proposal;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Where(clause = "is_closed = false")
@SQLDelete(sql = "UPDATE user_proposal SET is_closed = true, status = 'FINISHED' WHERE id = ?")
@NoArgsConstructor(access = PROTECTED)
public class UserProposal extends BaseTimeEntity {

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
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "userProposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpertBid> expertBidList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private boolean isClosed;
    @Builder
    public UserProposal(User user, SubCategory subCategory, String location, String detailedDescription, LocalDateTime strDate) {
        isValidDate(strDate);
        this.user = user;
        this.subCategory = subCategory;
        this.location = location;
        this.detailedDescription = detailedDescription;
        this.preferredStartDate = strDate;
    }


    private void isValidDate(LocalDateTime strDate) {
        if (LocalDateTime.now().isAfter(strDate)) {
            throw new ApiException(ExceptionCode.DATE_IS_UNVAlID);
        }
    }

    public void updateStore(Store store) {
        this.store = store;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void addExpertBid(ExpertBid expertBid) {
        expertBidList.add(expertBid);
        expertBid.addUserProposal(this);
    }
}
