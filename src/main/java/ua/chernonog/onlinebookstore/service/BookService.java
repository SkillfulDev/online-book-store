package ua.chernonog.onlinebookstore.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import ua.chernonog.onlinebookstore.dto.request.book.BookSearchParametersDto;
import ua.chernonog.onlinebookstore.dto.request.book.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.book.BookDtoWithoutCategoryIds;
import ua.chernonog.onlinebookstore.dto.response.book.BookResponseDto;
import ua.chernonog.onlinebookstore.entity.Category;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto bookDto);

    List<BookResponseDto> getAll(Pageable pageable);

    BookResponseDto getById(Long id);

    void deleteById(Long id);

    BookResponseDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookResponseDto> search(BookSearchParametersDto searchParameters, Pageable pageable);

    List<BookDtoWithoutCategoryIds> getBookByCategory(Category category, Pageable pageable);
}
