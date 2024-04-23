package ua.chernonog.onlinebookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.request.category.CreateCategoryDto;
import ua.chernonog.onlinebookstore.dto.response.book.BookDtoWithoutCategoryIds;
import ua.chernonog.onlinebookstore.dto.response.category.CategoryResponseDto;
import ua.chernonog.onlinebookstore.entity.Category;
import ua.chernonog.onlinebookstore.exception.EntityNotFoundException;
import ua.chernonog.onlinebookstore.mapper.CategoryMapper;
import ua.chernonog.onlinebookstore.repository.category.CategoryRepository;
import ua.chernonog.onlinebookstore.service.BookService;
import ua.chernonog.onlinebookstore.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BookService bookService;

    @Override
    public CategoryResponseDto save(CreateCategoryDto categoryDto) {
        return categoryMapper.toDto(
                categoryRepository.save(
                        categoryMapper.toModel(
                                categoryDto)));
    }

    @Override
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryMapper.toDto(getCategoryIfExist(id));
    }

    @Override
    public CategoryResponseDto updateById(Long id, CreateCategoryDto createCategoryDto) {
        Category categoryFromDb = getCategoryIfExist(id);
        Category updatedCategory = updateCategoryFields(categoryFromDb, createCategoryDto);

        return categoryMapper.toDto(categoryRepository.save(updatedCategory));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(
                getCategoryIfExist(id).getId());
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBookByCategory(Long id, Pageable pageable) {
        return bookService.getBookByCategory(id, pageable);
    }

    private Category getCategoryIfExist(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can`t find category with id " + id + " in DB"));
    }

    private Category updateCategoryFields(
            Category category,
            CreateCategoryDto createCategoryDto
    ) {
        category.setId(category.getId());
        category.setName(createCategoryDto.getName());
        category.setDescription(createCategoryDto.getDescription());
        return category;
    }
}
