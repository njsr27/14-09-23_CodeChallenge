package com.example.demo.controller.response.user;

import com.example.demo.provider.response.RandomUserResponseTree.User;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@Builder
public class GeneratedUsersTreeResponse {
    Map<String, Map<String, Map<String, List<User>>>> countries;
}
