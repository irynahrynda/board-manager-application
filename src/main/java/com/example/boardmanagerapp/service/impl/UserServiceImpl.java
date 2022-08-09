package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.dto.request.UserRequestDto;
import com.example.boardmanagerapp.dto.response.UserResponseDto;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.User;
import com.example.boardmanagerapp.repository.UserRepository;
import com.example.boardmanagerapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final MapperToModel<UserRequestDto, User> userMapperToModel;
    private final MapperToDto<UserResponseDto, User> userMapperToDto;

    public UserServiceImpl(PasswordEncoder encoder,
                           UserRepository userRepository,
                           MapperToModel<UserRequestDto, User> userMapperToModel,
                           MapperToDto<UserResponseDto, User> userMapperToDto) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.userMapperToModel = userMapperToModel;
        this.userMapperToDto = userMapperToDto;
    }


    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userMapperToDto.mapToDto(userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get user by id " + id)));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }
}
