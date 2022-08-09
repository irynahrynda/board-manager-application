package com.example.boardmanagerapp.service;


import com.example.boardmanagerapp.model.Role;

public interface RoleService {
    Role add(Role role);

    Role findAllByRoleName(String roleName);
}
