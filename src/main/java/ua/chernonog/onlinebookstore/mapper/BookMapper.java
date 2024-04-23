package ua.chernonog.onlinebookstore.mapper;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ua.chernonog.onlinebookstore.config.MapperConfig;
import ua.chernonog.onlinebookstore.dto.request.book.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.book.BookDtoWithoutCategoryIds;
import ua.chernonog.onlinebookstore.dto.response.book.BookResponseDto;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.entity.Category;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(target = "categories", ignore = true)
    BookResponseDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookResponseDto bookDto, Book book) {
        if (book.getCategories() != null) {
            Set<Long> categoryIds = book.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            bookDto.setCategories(categoryIds);
        } else {
            bookDto.setCategories(Collections.emptySet());
        }
    }
}
