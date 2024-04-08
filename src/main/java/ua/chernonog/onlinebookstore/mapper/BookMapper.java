package ua.chernonog.onlinebookstore.mapper;

import org.mapstruct.Mapper;
import ua.chernonog.onlinebookstore.config.MapperConfig;
import ua.chernonog.onlinebookstore.dto.request.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.BookResponseDto;
import ua.chernonog.onlinebookstore.entity.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookResponseDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}

