package com.example.demo.service;

import com.example.demo.controller.request.user.UserUpdateRequest;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.provider.RandomUserProviderClient;
import com.example.demo.provider.response.RandomUserResponseTree;
import com.example.demo.service.mapper.RandomUserResponseToUserMapper;
import com.example.demo.service.mapper.RandomUserResponseTreeToUsersTreeResponseMapper;
import com.example.demo.service.mapper.UserEntityToUserMapper;
import com.example.demo.service.validation.GenderValidator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.example.demo.constants.RandomUserProviderConstants.RANDOM_USER_PROVIDER_FIELDS;
import static com.example.demo.constants.RandomUserProviderConstants.RANDOM_USER_PROVIDER_FIELDS_TREE;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RandomUserProviderClient randomUserProviderClient;
    private final UserEntityToUserMapper userEntityToUserMapper;
    private final RandomUserResponseToUserMapper randomUserResponseToUserMapper;
    private final RandomUserResponseTreeToUsersTreeResponseMapper randomUserResponseTreeToUsersTreeResponseMapper;
    private final GenderValidator genderValidator;

    public List<User> getUsers() {
        return userRepository.findAll()
            .stream()
            .map(userEntityToUserMapper::map)
            .toList();
    }

    public List<User> getUsersWithPagination(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size))
            .stream()
            .map(userEntityToUserMapper::map)
            .toList();
    }

    @SneakyThrows
    public User getUserById(String username) {
        return userRepository.findById(username)
            .map(userEntityToUserMapper::map)
            .orElseThrow(() -> new UserNotFoundException(username));
    }

    @SneakyThrows
    public User createUser(User user) {
        if (userRepository.existsById(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername());
        } else {
            userRepository.save(userEntityToUserMapper.map(user));
            return user;
        }
    }

    @SneakyThrows
    public User updateUser(String username, UserUpdateRequest userUpdateRequest) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(username);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            User user = com.example.demo.model.User.builder()
                .username(username)
                .name(Objects.isNull(userUpdateRequest.getName()) ? userEntity.getName():userUpdateRequest.getName())
                .email(Objects.isNull(userUpdateRequest.getEmail()) ? userEntity.getEmail():userUpdateRequest.getEmail())
                .gender(Objects.isNull(userUpdateRequest.getGender()) ? userEntity.getGender().name():genderValidator.validate(userUpdateRequest.getGender()).name())
                .picture(Objects.isNull(userUpdateRequest.getPicture()) ? userEntity.getPicture():userUpdateRequest.getPicture())
                .build();
            userRepository.save(userEntityToUserMapper.map(user));
            return user;
        } else {
            throw new UserNotFoundException(username);
        }
    }

    @SneakyThrows
    public User deleteUserById(String username) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(username);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            User user = com.example.demo.model.User.builder()
                .username(username)
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .gender(userEntity.getGender().name())
                .picture(userEntity.getPicture())
                .build();
            userRepository.deleteById(username);
            return user;
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public List<User> generateUsers(Integer amount) {
        return this.createUsers(
            randomUserProviderClient.generateRandomUsers(RANDOM_USER_PROVIDER_FIELDS, amount)
                .getResults()
                .stream()
                .map(randomUserResponseToUserMapper::map)
                .toList()
        );
    }

    public Map<String, Map<String, Map<String, List<RandomUserResponseTree.User>>>> generateUsersTree(Integer amount) {
        return randomUserResponseTreeToUsersTreeResponseMapper.map(
            randomUserProviderClient.generateRandomUsersTree(RANDOM_USER_PROVIDER_FIELDS_TREE, amount)
        );
    }

    @SneakyThrows
    public List<User> createUsers(List<User> users) {
        List<User> result = new ArrayList<>();
        StringBuilder existingUsernames = new StringBuilder(" ");

        for (User user : users) {
            if (userRepository.existsById(user.getUsername())) {
                existingUsernames.append(user.getUsername()).append(" ");
            } else {
                userRepository.save(userEntityToUserMapper.map(user));
                result.add(user);
            }
        }

        if (existingUsernames.length() > 1) {
            throw new UserAlreadyExistsException(existingUsernames);
        }

        return result;
    }

}
