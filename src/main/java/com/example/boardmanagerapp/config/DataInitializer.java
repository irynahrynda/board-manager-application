package com.example.boardmanagerapp.config;

import com.example.boardmanagerapp.model.Role;
import com.example.boardmanagerapp.model.User;
import com.example.boardmanagerapp.service.RoleService;
import com.example.boardmanagerapp.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.save(userRole);
        User admin = new User();
        admin.setEmail("admin@i.ua");
        admin.setPassword("admin123");
       admin.setRoles(Set.of(adminRole));
        userService.save(admin);
        Role role = new Role();
        role.setRoleName(Role.RoleName.USER);
        roleService.save(role);
        User user = new User();
        user.setEmail("user@i.ua");
        user.setPassword("user123");
        user.setRoles(Set.of(role));
        userService.save(user);
    }
}
