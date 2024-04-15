package ua.chernonog.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.chernonog.onlinebookstore.dto.request.UserLoginRequestDto;
import ua.chernonog.onlinebookstore.dto.request.UserRegistrationRequestDto;
import ua.chernonog.onlinebookstore.dto.response.UserLoginResponseDto;
import ua.chernonog.onlinebookstore.dto.response.UserResponseDto;
import ua.chernonog.onlinebookstore.exception.RegistrationException;
import ua.chernonog.onlinebookstore.security.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "User authentication", description = "Endpoints for authenticate users")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @Operation(summary = "Register a user", description = "Register a user")
    public UserResponseDto register(
            @Valid @RequestBody UserRegistrationRequestDto requestDto
    ) throws RegistrationException {
        return authenticationService.register(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Login user")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
