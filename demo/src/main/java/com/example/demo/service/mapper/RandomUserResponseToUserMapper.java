package com.example.demo.service.mapper;

import com.example.demo.provider.response.RandomUserResponse.User;
import com.example.demo.service.validation.GenderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomUserResponseToUserMapper {

    private final GenderValidator genderValidator;

    public com.example.demo.model.User map(User user) {
        return com.example.demo.model.User.builder()
            .username(user.login.username)
            .name(user.name.first + " " + user.name.last)
            .email(user.email)
            .gender(genderValidator.validate(user.gender).name())
            .picture(user.picture.large)
            .build();
    }

}
