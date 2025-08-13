package com.lidn.kdrama_app.services.userServices;

import java.util.List;

import com.lidn.kdrama_app.dto.UserDto;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUser(Long id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
