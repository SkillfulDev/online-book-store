package ua.chernonog.onlinebookstore.exception.dto;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
        LocalDateTime time,
        HttpStatus status,
        List<String> error
) {
}
