package com.example.boardmanagerapp.config;

import com.example.boardmanagerapp.model.Role;
import com.example.boardmanagerapp.model.User;
import com.example.boardmanagerapp.repository.RoleRepository;
import com.example.boardmanagerapp.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
       roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleRepository.save(userRole);

        User admin = new User();
        admin.setEmail("admin@i.ua");
        admin.setPassword("admin123");
        admin.setRoles(Set.of(adminRole));
        userRepository.save(admin);

        User user = new User();
        user.setEmail("user@i.ua");
        user.setPassword("user123");
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
    }
}
