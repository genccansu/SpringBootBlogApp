package com.example.folksdev.projectblog.service;

import com.example.folksdev.projectblog.TestSupport;
import com.example.folksdev.projectblog.dto.UserDto;
import com.example.folksdev.projectblog.dto.converter.UserDtoConverter;
import com.example.folksdev.projectblog.dto.ruquest.CreateUserRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdateUserRequest;
import com.example.folksdev.projectblog.exception.UserNotFoundException;
import com.example.folksdev.projectblog.model.User;
import com.example.folksdev.projectblog.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends TestSupport {
    private UserRepository userRepository;
    private UserDtoConverter userDtoConverter;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository= Mockito.mock(UserRepository.class);
        userDtoConverter=Mockito.mock(UserDtoConverter.class);

        userService = new UserService(userRepository,userDtoConverter);
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void testCreateUser_whenCreateUserRequest_shouldReturnCreateUserDto(){

        CreateUserRequest createUserRequest = generateCreateUserRequest();
        User user= generateUser();
        UserDto exceptedUserDto = generateUserDto();

        Mockito.when(userDtoConverter.convertToUserDto(user)).thenReturn(exceptedUserDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.createUser(createUserRequest);

        assertEquals(exceptedUserDto,result);

        Mockito.verify(userDtoConverter).convertToUserDto(user);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    void testGetUserById_whenIdNotExists_shouldThrowUserNotFoundException(){
        Mockito.when(userRepository.findById("id")).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                ()->userService.getUserById("id"));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);
    }

    @Test
    void testGetUserById_whenIdExist_shouldReturnUserDto(){

        User user = generateUser();
        UserDto exceptedUserDto = generateUserDto();

        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(user));
        Mockito.when(userDtoConverter.convertToUserDto(user)).thenReturn(exceptedUserDto);

        UserDto result= userService.getUserById("id");

        assertEquals(exceptedUserDto,result);

        Mockito.verify(userRepository).findById("id");
        Mockito.verify(userDtoConverter).convertToUserDto(user);
    }

    @Test
    void testUpdateUser_whenUserIdExist_shouldReturnUpdateUserDto(){

        UpdateUserRequest updateUserRequest = generateUpdateUserRequest();
        User updatedUser =generateUpdatedUser(generateUser(),updateUserRequest);
        UserDto exceptedUserDto = generateUserDto();

        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(generateUser()));
        Mockito.when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        Mockito.when(userDtoConverter.convertToUserDto(updatedUser)).thenReturn(exceptedUserDto);

        UserDto result= userService.updateUser("id",updateUserRequest);

        assertEquals(exceptedUserDto,result);

        Mockito.verify(userRepository).findById("id");
        Mockito.verify(userDtoConverter).convertToUserDto(updatedUser);
        Mockito.verify(userRepository).save(updatedUser);


    }
    @Test
    void testUpdateUser_whenUserIdNotExist_shouldThrowUserNotFoundException(){
        UpdateUserRequest updateUserRequest = generateUpdateUserRequest();

        Mockito.when(userRepository.findById("id")).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class,
                ()->userService.updateUser("id",updateUserRequest));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);
    }

    @Test
    void testDeleteUser_whenUserIdExist_shouldReturnString(){

        User user = generateUser();
        UserDto userDto = generateUserDto();

        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(user));
        Mockito.when(userDtoConverter.convertToUserDto(user)).thenReturn(userDto);

        String result= userService.deleteUserById("id");
        assertEquals("id user deleted",result);

        Mockito.verify(userDtoConverter).convertToUserDto(user);
        Mockito.verify(userRepository).findById("id");
    }

    @Test
    void testDeleteUser_whenUserIdNotExist_shouldThrowUserNotFoundException(){

        Mockito.when(userRepository.findById("id")).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                ()-> userService.findByUserId("id"));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);
    }

    @Test
    void testGetAllUsers_shouldReturnListUserDto(){
        List<User> userList = generateUserList();
        List<UserDto> exceptedUserDtoList = generateListUserDto();

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userDtoConverter.convertToUserDtoList(userList)).thenReturn(exceptedUserDtoList);

       List<UserDto> result =userService.getAllUserList();

       assertEquals(exceptedUserDtoList,result);

       Mockito.verify(userRepository).findAll();
       Mockito.verify(userDtoConverter).convertToUserDtoList(userList);
    }

}