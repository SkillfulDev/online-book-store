package ua.chernonog.onlinebookstore.service;

import java.util.List;
import ua.chernonog.onlinebookstore.dto.request.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.BookDto;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll();

    BookDto findBookById(Long id);
}
