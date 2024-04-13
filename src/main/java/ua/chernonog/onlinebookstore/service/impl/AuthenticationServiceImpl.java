package ua.chernonog.onlinebookstore.service.impl;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.request.UserRegistrationRequestDto;
import ua.chernonog.onlinebookstore.dto.response.UserResponseDto;
import ua.chernonog.onlinebookstore.entity.Role;
import ua.chernonog.onlinebookstore.entity.RoleName;
import ua.chernonog.onlinebookstore.entity.User;
import ua.chernonog.onlinebookstore.exception.RegistrationException;
import ua.chernonog.onlinebookstore.mapper.UserMapper;
import ua.chernonog.onlinebookstore.repository.user.RoleRepository;
import ua.chernonog.onlinebookstore.repository.user.UserRepository;
import ua.chernonog.onlinebookstore.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email " + requestDto.getEmail()
                    + " has already exist ");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role role = roleRepository.findByName(RoleName.USER).orElseThrow(
                RuntimeException::new);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userMapper.toDto(userRepository.save(user));
    }
}
