package ua.chernonog.onlinebookstore.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import ua.chernonog.onlinebookstore.dto.request.BookSearchParametersDto;
import ua.chernonog.onlinebookstore.dto.request.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.BookDto;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findBookById(Long id);

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParametersDto searchParameters, Pageable pageable);
}
