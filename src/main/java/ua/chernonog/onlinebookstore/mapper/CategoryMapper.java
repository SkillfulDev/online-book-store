package ua.chernonog.onlinebookstore.mapper;

import org.mapstruct.Mapper;
import ua.chernonog.onlinebookstore.config.MapperConfig;
import ua.chernonog.onlinebookstore.dto.request.category.CreateCategoryDto;
import ua.chernonog.onlinebookstore.dto.response.category.CategoryResponseDto;
import ua.chernonog.onlinebookstore.entity.Category;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toModel(CreateCategoryDto createCategoryDto);

    CategoryResponseDto toDto(Category category);
}
