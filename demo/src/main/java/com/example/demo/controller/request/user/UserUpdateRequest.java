package com.example.demo.controller.request.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserUpdateRequest {
    
    String name;
    String email;
    String gender;
    String picture;

}
