package ua.chernonog.onlinebookstore.security;

import ua.chernonog.onlinebookstore.dto.request.user.UserLoginRequestDto;
import ua.chernonog.onlinebookstore.dto.request.user.UserRegistrationRequestDto;
import ua.chernonog.onlinebookstore.dto.response.user.UserLoginResponseDto;
import ua.chernonog.onlinebookstore.dto.response.user.UserResponseDto;
import ua.chernonog.onlinebookstore.exception.RegistrationException;

public interface AuthenticationService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws
            RegistrationException;

    UserLoginResponseDto authenticate(UserLoginRequestDto request);
}
