package com.flab.skilltrademarket.domain.category;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class SubCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    private String name;

    private String description;

    @Builder
    public SubCategory(Category category, String name, String description) {
        this.category = category;
        this.name = name;
        this.description = description;
    }

}
