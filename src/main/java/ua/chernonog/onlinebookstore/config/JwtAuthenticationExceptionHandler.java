package ua.chernonog.onlinebookstore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.chernonog.onlinebookstore.exception.JwtTokenValidException;
import ua.chernonog.onlinebookstore.exception.dto.ErrorDto;

@Slf4j
@Component
public class JwtAuthenticationExceptionHandler extends OncePerRequestFilter {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtTokenValidException e) {
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            ErrorDto errorDto = new ErrorDto(LocalDateTime.now(), HttpStatus.UNAUTHORIZED,
                    List.of(e.getMessage()));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print(mapper.writeValueAsString(errorDto));
            response.flushBuffer();
        }
    }
}
