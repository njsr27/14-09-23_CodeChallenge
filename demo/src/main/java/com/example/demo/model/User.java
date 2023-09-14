package com.example.demo.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;

@Value
@Builder
public class User {

    @NonNull
    String username;

    @NonNull
    String name;

    @NonNull
    String email;

    @NonNull
    String gender;

    @NonNull
    String picture;

}
