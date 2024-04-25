package ua.chernonog.onlinebookstore.security;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.request.user.UserLoginRequestDto;
import ua.chernonog.onlinebookstore.dto.request.user.UserRegistrationRequestDto;
import ua.chernonog.onlinebookstore.dto.response.user.UserLoginResponseDto;
import ua.chernonog.onlinebookstore.dto.response.user.UserResponseDto;
import ua.chernonog.onlinebookstore.entity.Role;
import ua.chernonog.onlinebookstore.entity.RoleName;
import ua.chernonog.onlinebookstore.entity.User;
import ua.chernonog.onlinebookstore.exception.RegistrationException;
import ua.chernonog.onlinebookstore.mapper.UserMapper;
import ua.chernonog.onlinebookstore.repository.user.RoleRepository;
import ua.chernonog.onlinebookstore.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email " + requestDto.getEmail()
                    + " has already exist ");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role role = roleRepository.findByName(RoleName.USER).orElseThrow(() ->
                new NoSuchElementException("Role USER not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())

        );

        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);
    }
}
