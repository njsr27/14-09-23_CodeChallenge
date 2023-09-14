package com.example.demo.provider.fallback;

import com.example.demo.provider.RandomUserProviderClient;
import com.example.demo.provider.response.RandomUserResponse;
import com.example.demo.provider.response.RandomUserResponseTree;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RandomUserProviderClientFallback implements RandomUserProviderClient {
    @Override
    public RandomUserResponse generateRandomUsers(String[] inc, Integer results) {
        return new RandomUserResponse(new ArrayList<>());
    }

    @Override
    public RandomUserResponseTree generateRandomUsersTree(String[] inc, Integer results) {
        return new RandomUserResponseTree(new ArrayList<>());
    }
}
