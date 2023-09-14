package com.example.demo.service.mapper;

import com.example.demo.provider.response.RandomUserResponseTree;
import com.example.demo.provider.response.RandomUserResponseTree.User;
import com.example.demo.service.validation.GenderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RandomUserResponseTreeToUsersTreeResponseMapper {

    private final GenderValidator genderValidator;

    public Map<String, Map<String, Map<String, List<User>>>> map(RandomUserResponseTree randomUserResponseTree) {
        Map<String, Map<String, Map<String, List<User>>>> countries = new HashMap<>();

        for (User user : randomUserResponseTree.getResults()) {
            String userCountry = user.location.country;
            String userState = user.location.state;
            String userCity = user.location.city;
            if (countries.containsKey(userCountry)) {
                Map<String, Map<String, List<User>>> states = countries.get(userCountry);
                if (states.containsKey(userState)) {
                    Map<String, List<User>> cities = states.get(userState);
                    if (cities.containsKey(userCity)) {
                        cities.get(userCity).add(user);
                    } else {
                        cities.put(
                            userCity,
                            new ArrayList<>(List.of(user))
                        );
                    }
                } else {
                    states.put(
                        userState,
                        new HashMap<>(Map.of(
                            userCity,
                            new ArrayList<>(List.of(user))
                        ))
                    );
                }
            } else {
                countries.put(
                    userCountry,
                    new HashMap<>(Map.of(
                        userState,
                        new HashMap<>(Map.of(
                            userCity,
                            new ArrayList<>(List.of(user))
                        ))
                    ))
                );
            }
        }

        return countries;
    }

}
