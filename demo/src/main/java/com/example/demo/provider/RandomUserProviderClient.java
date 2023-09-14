package com.example.demo.provider;

import com.example.demo.provider.fallback.RandomUserProviderClientFallback;
import com.example.demo.provider.response.RandomUserResponse;
import com.example.demo.provider.response.RandomUserResponseTree;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    value = "randomUser",
    url = "https://randomuser.me",
    fallback = RandomUserProviderClientFallback.class
)
public interface RandomUserProviderClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api", produces = "application/json")
    RandomUserResponse generateRandomUsers(@RequestParam String[] inc, @RequestParam Integer results);

    @RequestMapping(method = RequestMethod.GET, value = "/api", produces = "application/json")
    RandomUserResponseTree generateRandomUsersTree(@RequestParam String[] inc, @RequestParam Integer results);
}
