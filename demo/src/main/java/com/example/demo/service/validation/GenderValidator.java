package com.example.demo.service.validation;

import com.example.demo.exception.InvalidGenderException;
import com.example.demo.persistence.entity.UserEntity.Gender;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GenderValidator {

    public Gender validate(String value) {
        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidGenderException("Invalid gender. Please use one of these values: " + Arrays.toString(Gender.values()));
        }
    }

}
