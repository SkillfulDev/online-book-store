package ua.chernonog.onlinebookstore.security;

import ua.chernonog.onlinebookstore.dto.request.UserLoginRequestDto;
import ua.chernonog.onlinebookstore.dto.request.UserRegistrationRequestDto;
import ua.chernonog.onlinebookstore.dto.response.UserLoginResponseDto;
import ua.chernonog.onlinebookstore.dto.response.UserResponseDto;
import ua.chernonog.onlinebookstore.exception.RegistrationException;

public interface AuthenticationService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws
            RegistrationException;

    UserLoginResponseDto authenticate(UserLoginRequestDto request);
}
