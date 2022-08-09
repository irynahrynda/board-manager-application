package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.dto.request.UserRequestDto;
import com.example.boardmanagerapp.dto.response.UserResponseDto;
import com.example.boardmanagerapp.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    UserResponseDto getUserById(Long id);

    Optional<User> findByEmail(String email);
}
