package com.flab.skilltrademarket.domain.expert;

import com.flab.skilltrademarket.domain.skill.Skill;
import com.flab.skilltrademarket.domain.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Expert {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "expert", cascade = ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();


}
