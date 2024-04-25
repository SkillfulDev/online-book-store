package ua.chernonog.onlinebookstore.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenValidException extends AuthenticationException {
    public JwtTokenValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtTokenValidException(String message) {
        super(message);
    }
}
