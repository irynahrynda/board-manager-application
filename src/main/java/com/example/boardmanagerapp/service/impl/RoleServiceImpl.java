package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.model.Role;
import com.example.boardmanagerapp.repository.RoleRepository;
import com.example.boardmanagerapp.service.RoleService;
import org.springframework.stereotype.Service;



@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findAllByRoleName(String roleName) {
        return roleRepository.findAllByRoleName(roleName).orElseThrow(
                () -> new RuntimeException("Can't get role by role name: " + roleName));
    }
}
