package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.dto.request.UserRequestDto;
import com.example.boardmanagerapp.dto.response.UserResponseDto;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Role;
import com.example.boardmanagerapp.model.User;
import com.example.boardmanagerapp.repository.UserRepository;
import com.example.boardmanagerapp.service.AuthenticationService;
import com.example.boardmanagerapp.service.RoleService;
import com.example.boardmanagerapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final MapperToModel<UserRequestDto, User> userMapperToModel;
    private final MapperToDto<UserResponseDto, User> userMapperToDto;

    public AuthenticationServiceImpl(UserService userService, UserRepository userRepository, RoleService roleService, MapperToModel<UserRequestDto, User> userMapperToModel, MapperToDto<UserResponseDto, User> userMapperToDto) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userMapperToModel = userMapperToModel;
        this.userMapperToDto = userMapperToDto;
    }


    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.findAllByRoleName(Role.RoleName.USER.name())));
        userService.save(user);
        return user;
    }
}
