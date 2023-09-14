package com.example.demo.service;

import com.example.demo.controller.request.user.UserUpdateRequest;
import com.example.demo.exception.InvalidGenderException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.provider.response.RandomUserResponseTree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        Assert.notNull(userService, "Main instance must not be null");
    }

    @Test
    void getUsersRetrievesCorrectly() {
        List<User> users = userService.getUsers();
        Assert.notNull(users, "List of users must not be null");
        Assert.isTrue(users.size() > 0, "List must not be empty");
    }

    @Test
    void getUserByIdRetrievesCorrectly() {
        User user = userService.getUserById("testUsername1");
        Assert.notNull(user, "User instance must not be null");
        Assert.isTrue(user.getUsername().equals("testUsername1"), "User must have valid username");
        assertThrows(UserNotFoundException.class, () -> userService.getUserById("00000"));
    }

    @Test
    void createUserCreatesCorrectly() {
        userService.createUser(
            User.builder()
                .username("testUsername10")
                .name("testName10")
                .email("testEmail10")
                .gender("male")
                .picture("testPicture10")
                .build()
        );
        User user = userService.getUserById("testUsername10");
        Assert.notNull(user, "User instance must not be null");
        Assert.isTrue(user.getUsername().equals("testUsername10"), "User must have valid username");

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(
                User.builder()
                    .username("testUsername10")
                    .name("testName10")
                    .email("testEmail10")
                    .gender("male")
                    .picture("testPicture10")
                    .build()
            )
        );

        assertThrows(InvalidGenderException.class, () -> userService.createUser(
                User.builder()
                    .username("testUsername99")
                    .name("testName10")
                    .email("testEmail10")
                    .gender("00000")
                    .picture("testPicture10")
                    .build()
            )
        );
    }

    @Test
    void updateUserUpdatesCorrectly() {
        User user = userService.getUserById("testUsername1");
        Assert.notNull(user, "User instance must not be null");
        Assert.isTrue(user.getUsername().equals("testUsername1"), "User must have valid username");
        Assert.isTrue(user.getGender().equals("male"), "User must have valid gender");
        userService.updateUser(
            "testUsername1",
            UserUpdateRequest.builder()
                .gender("female")
                .build()
        );
        user = userService.getUserById("testUsername1");
        Assert.isTrue(user.getGender().equals("female"), "User must have valid gender");

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(
                "00000",
                UserUpdateRequest.builder()
                    .gender("female")
                    .build()
            )
        );
    }

    @Test
    void deleteUserDeletesCorrectly() {
        userService.createUser(
            User.builder()
                .username("testUsername99")
                .name("testName10")
                .email("testEmail10")
                .gender("male")
                .picture("testPicture10")
                .build()
        );
        User user = userService.getUserById("testUsername99");
        Assert.notNull(user, "User instance must not be null");
        Assert.isTrue(user.getUsername().equals("testUsername99"), "User must have valid username");

        userService.deleteUserById("testUsername99");

        assertThrows(UserNotFoundException.class, () -> userService.getUserById("testUsername99"));
    }

    @Test
    void generateUsersGeneratesCorrectly() {
        List<User> generatedUsers = userService.generateUsers(2);
        Assert.notNull(generatedUsers, "List of users must not be null");
        Assert.isTrue(generatedUsers.size()==2, "List must not be empty");

        User createdUser = userService.getUserById(generatedUsers.get(0).getUsername());
        Assert.notNull(createdUser, "Created user must not be null");
    }

    @Test
    void generateUsersTreeGeneratesCorrectly() {
        Map<String, Map<String, Map<String, List<RandomUserResponseTree.User>>>> generatedUsers = userService.generateUsersTree(10);
        Assert.notNull(generatedUsers, "Map of users must not be null");
        Assert.isTrue(generatedUsers.size() > 0, "Map must not be empty");
    }
}
