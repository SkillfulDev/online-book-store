package ua.chernonog.onlinebookstore.service;

import ua.chernonog.onlinebookstore.dto.request.UserRegistrationRequestDto;
import ua.chernonog.onlinebookstore.dto.response.UserResponseDto;

public interface AuthenticationService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
