package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
