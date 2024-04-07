package ua.chernonog.onlinebookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.request.UserRegistrationRequestDto;
import ua.chernonog.onlinebookstore.dto.response.UserResponseDto;
import ua.chernonog.onlinebookstore.entity.User;
import ua.chernonog.onlinebookstore.mapper.UserMapper;
import ua.chernonog.onlinebookstore.repository.user.UserRepository;
import ua.chernonog.onlinebookstore.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        User user = userMapper.toModel(requestDto);
        return userMapper.toDto(userRepository.save(user));
    }
}
