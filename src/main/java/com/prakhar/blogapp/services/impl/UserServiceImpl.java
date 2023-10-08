package com.prakhar.blogapp.services.impl;

import com.prakhar.blogapp.models.Role;
import com.prakhar.blogapp.payloads.UserDto;
import com.prakhar.blogapp.exceptions.ResourceNotFoundException;
import com.prakhar.blogapp.models.User;
import com.prakhar.blogapp.repositories.RoleRepository;
import com.prakhar.blogapp.repositories.UserRepository;
import com.prakhar.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("No Role with id " + 1));
        user.getRoles().add(role);
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDTO) {
        User user = mapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        List<User> allUsers = page.getContent();
        List<UserDto> userDtos = allUsers.stream().map(user -> mapper.map(user, UserDto.class)).toList();
        return new PageImpl<>(userDtos, pageable, userDtos.size());
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDTO, Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
