package com.example.folksdev.projectblog.controller;

import com.example.folksdev.projectblog.dto.UserDto;
import com.example.folksdev.projectblog.dto.ruquest.CreateUserRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdateUserRequest;
import com.example.folksdev.projectblog.model.User;
import com.example.folksdev.projectblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/findall")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUserList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(path = "/displayname/{id}")
    public ResponseEntity<String> getDisplayNameByUserId(@PathVariable String id) {
        return ResponseEntity.ok(userService.getDisplayNameById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        UserDto createUser = userService.createUser(userRequest);
        return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUserById(@Valid @PathVariable String id,
                                                  @RequestBody UpdateUserRequest userRequest) {
        UserDto updatedUser = userService.updateUser(id, userRequest);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable String id) {
        String deleteUser = userService.deleteUserById(id);
        return new ResponseEntity<String>(deleteUser, HttpStatus.ACCEPTED);
    }
}
