package ua.chernonog.onlinebookstore.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import ua.chernonog.onlinebookstore.dto.request.category.CreateCategoryDto;
import ua.chernonog.onlinebookstore.dto.response.book.BookDtoWithoutCategoryIds;
import ua.chernonog.onlinebookstore.dto.response.category.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto save(CreateCategoryDto categoryDto);

    List<CategoryResponseDto> getAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto updateById(Long id, CreateCategoryDto createCategoryDto);

    void deleteById(Long id);

    List<BookDtoWithoutCategoryIds> getBookByCategory(Long id, Pageable pageable);
}
