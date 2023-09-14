package com.example.demo.service.mapper;

import com.example.demo.model.User;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.service.validation.GenderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityToUserMapper {

    private final GenderValidator genderValidator;

    public User map(UserEntity userEntity) {
        return User.builder()
            .username(userEntity.getUsername())
            .name(userEntity.getName())
            .email(userEntity.getEmail())
            .gender(userEntity.getGender().name().toLowerCase())
            .picture(userEntity.getPicture())
            .build();
    }

    public UserEntity map(User user) {
        return UserEntity.builder()
            .username(user.getUsername())
            .name(user.getName())
            .email(user.getEmail())
            .gender(genderValidator.validate(user.getGender()))
            .picture(user.getPicture())
            .build();
    }

}
