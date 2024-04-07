package ua.chernonog.onlinebookstore.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import ua.chernonog.onlinebookstore.dto.request.BookSearchParametersDto;
import ua.chernonog.onlinebookstore.dto.request.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.BookResponseDto;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto bookDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto findBookById(Long id);

    void deleteById(Long id);

    BookResponseDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookResponseDto> search(BookSearchParametersDto searchParameters, Pageable pageable);
}
