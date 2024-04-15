package ua.chernonog.onlinebookstore.service;

import ua.chernonog.onlinebookstore.dto.request.UserRegistrationRequestDto;
import ua.chernonog.onlinebookstore.dto.response.UserResponseDto;
import ua.chernonog.onlinebookstore.exception.RegistrationException;

public interface AuthenticationService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws
            RegistrationException;
}
