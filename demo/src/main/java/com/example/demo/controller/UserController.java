package com.example.demo.controller;

import com.example.demo.controller.request.user.UserUpdateRequest;
import com.example.demo.controller.response.user.GeneratedUsersTreeResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok()
            .body(userService.getUsers());
    }

    @GetMapping("/users/pagination")
    ResponseEntity<List<User>> getUsersPagination(@RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        return ResponseEntity.ok()
            .body(userService.getUsersWithPagination(page, size));
    }

    @GetMapping("/users/{username}")
    ResponseEntity<User> getById(@PathVariable String username) {
        return ResponseEntity.ok()
            .body(userService.getUserById(username));
    }

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok()
            .body(userService.createUser(user));
    }

    @PutMapping("/users/{username}")
    ResponseEntity<User> updateUser(@PathVariable String username,
                                    @RequestBody UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok()
            .body(userService.updateUser(username, userUpdateRequest));
    }

    @DeleteMapping("/users/{username}")
    ResponseEntity<User> deleteById(@PathVariable String username) {
        return ResponseEntity.ok()
            .body(userService.deleteUserById(username));
    }

    @GetMapping("/users/generate/{number}")
    ResponseEntity<List<User>> generateUsers(@PathVariable Integer number) {
        return ResponseEntity.ok()
            .body(userService.generateUsers(number));
    }

    //Done this way since we don't store country, state nor city in the database
    @GetMapping("/users/tree/{number}")
    ResponseEntity<GeneratedUsersTreeResponse> generateUsersTree(@PathVariable Integer number) {
        return ResponseEntity.ok()
            .body(
                GeneratedUsersTreeResponse.builder()
                    .countries(userService.generateUsersTree(number))
                    .build()
            );
    }
}
