package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.UserRequestDto;
import com.example.boardmanagerapp.dto.response.UserResponseDto;
import com.example.boardmanagerapp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements MapperToModel<UserRequestDto, User>,
        MapperToDto<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }

    @Override
    public User mapToModel(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        return user;
    }
}
