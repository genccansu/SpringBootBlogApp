package com.example.folksdev.projectblog.service;

import com.example.folksdev.projectblog.dto.UserDto;
import com.example.folksdev.projectblog.dto.converter.UserDtoConverter;
import com.example.folksdev.projectblog.dto.ruquest.CreateUserRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdateUserRequest;
import com.example.folksdev.projectblog.exception.UserNotFoundException;
import com.example.folksdev.projectblog.model.User;
import com.example.folksdev.projectblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository,
                       UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;

    }

    public UserDto createUser(CreateUserRequest userRequest) {
        User user = new User(
                userRequest.getName(),
                userRequest.getLastname(),
                userRequest.getDisplayName(),
                LocalDate.now(),
                userRequest.getEmail(),
                userRequest.getGender(),
                Collections.emptySet(),
                Collections.emptySet()
        );
        return userDtoConverter.convertToUserDto(userRepository.save(user));
    }

    protected List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public List<UserDto> getAllUserList() {
        return userDtoConverter.convertToUserDtoList(getAllUsers());
    }


    protected User findByUserId(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User could not found id " + id));
    }

    public UserDto getUserById(String id) {
        return userDtoConverter.convertToUserDto(findByUserId(id));
    }

    public UserDto updateUser(String id, UpdateUserRequest userRequest) {

        User user = findByUserId(id);

        User updatedUser = new User(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getDisplayName(),
                user.getCreationDate(),
                user.getEmail(),
                user.getGender(),
                user.getPosts(),
                user.getComments()

        );
        return userDtoConverter.convertToUserDto(userRepository.save(updatedUser));
    }

    public String deleteUserById(String id) {
        getUserById(id);
        userRepository.deleteById(id);

        return id + " user deleted";
    }

    public String getDisplayNameById(String id) {
        return userRepository.findDisplayNameByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("User could not find by id: " + id));
    }
}
