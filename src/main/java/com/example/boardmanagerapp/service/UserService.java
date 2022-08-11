package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.model.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    User findById(Long id);

    Optional<User> findByEmail(String email);
}
