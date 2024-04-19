package ua.chernonog.onlinebookstore.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenValidException extends AuthenticationException {
    public JwtTokenValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtTokenValidException(String msg) {
        super(msg);
    }
}
