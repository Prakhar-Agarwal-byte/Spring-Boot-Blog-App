package com.prakhar.blogapp.services;

import com.prakhar.blogapp.payloads.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto registerUser(UserDto user);
    UserDto createUser(UserDto user);
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto getUserById(Integer id);
    UserDto updateUser(UserDto user, Integer id);
    void deleteUser(Integer id);

}
