package com.flab.skilltrademarket.domain.user;

import com.flab.skilltrademarket.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Table(name="users")
@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String email;
    private String password;
    private String phone;
    @Enumerated(STRING)
    private UserRole userRole;

    @Builder
    public User(String email, String nickname, String password, String phone) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.userRole = UserRole.USER;
    }

    public void changeRole() {
        if (userRole.equals(UserRole.USER)) {
            userRole = UserRole.EXPERT;
        } else if (userRole.equals(UserRole.EXPERT)) {
            userRole = UserRole.USER;
        }
    }
}