package com.flab.skilltrademarket.domain.category;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCategory> subCategories = new ArrayList<>();

    @Builder
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void update(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
    }

}
