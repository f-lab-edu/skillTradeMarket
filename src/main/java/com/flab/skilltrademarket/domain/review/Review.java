package com.flab.skilltrademarket.domain.review;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.reply.Reply;
import com.flab.skilltrademarket.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private Reply reply;

    private String content;

    private double rating;


    @Builder
    public Review(User writer, Store store, SubCategory subCategory, String content, int rating) {
        this.writer = writer;
        this.store = store;
        this.subCategory = subCategory;
        this.content = content;
        this.rating = rating;

    }

    public void addReply(Reply reply) {
        this.reply = reply;
    }

}
