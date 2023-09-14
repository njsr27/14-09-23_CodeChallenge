package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_USERS")
public class UserEntity {

    @Id
    @Column(name = "pk_username")
    private String username;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String picture;

    public enum Gender {
        MALE,
        FEMALE
    }

}
