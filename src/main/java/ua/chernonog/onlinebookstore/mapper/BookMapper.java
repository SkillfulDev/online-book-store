package ua.chernonog.onlinebookstore.mapper;

import org.mapstruct.Mapper;
import ua.chernonog.onlinebookstore.config.MapperConfig;
import ua.chernonog.onlinebookstore.dto.request.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.BookDto;
import ua.chernonog.onlinebookstore.entity.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
