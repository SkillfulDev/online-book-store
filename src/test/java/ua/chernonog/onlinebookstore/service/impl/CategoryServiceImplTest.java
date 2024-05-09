package ua.chernonog.onlinebookstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.CATEGORY_1_NAME;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.CATEGORY_ID;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.NEW_CATEGORY_DESCRIPTION;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.NEW_CATEGORY_NAME;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.SPECIFIC_CATEGORY_NAME;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.DEFAULT_PAGE_NUMBER;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.DEFAULT_PAGE_SIZE;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ua.chernonog.onlinebookstore.dto.request.category.CreateCategoryDto;
import ua.chernonog.onlinebookstore.dto.response.category.CategoryResponseDto;
import ua.chernonog.onlinebookstore.entity.Category;
import ua.chernonog.onlinebookstore.mapper.CategoryMapper;
import ua.chernonog.onlinebookstore.repository.category.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("Create a new category")
    void createCategory_WithValidData_ShouldReturnCreatedCategory() {
        CreateCategoryDto createCategoryDto = new CreateCategoryDto();
        createCategoryDto.setName(NEW_CATEGORY_NAME);
        createCategoryDto.setDescription(NEW_CATEGORY_DESCRIPTION);

        Category category = new Category();
        category.setName(createCategoryDto.getName());
        category.setDescription(createCategoryDto.getDescription());

        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setName(createCategoryDto.getName());
        responseDto.setDescription(createCategoryDto.getDescription());

        when(categoryMapper.toModel(createCategoryDto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(responseDto);

        CategoryResponseDto result = categoryService.save(createCategoryDto);

        assertEquals(NEW_CATEGORY_NAME, result.getName());
        assertEquals(NEW_CATEGORY_DESCRIPTION, result.getDescription());
    }

    @Test
    @DisplayName("Retrieve all categories with pagination")
    void getAllCategories_WhenRequested_ShouldReturnAllCategories() {
        Pageable pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        Category category = new Category();
        category.setName(CATEGORY_1_NAME);

        List<Category> categoryList = List.of(category);
        PageImpl<Category> page = new PageImpl<>(categoryList);

        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setName(CATEGORY_1_NAME);

        when(categoryRepository.findAll(pageable)).thenReturn(page);
        when(categoryMapper.toDto(any(Category.class))).thenReturn(categoryDto);

        List<CategoryResponseDto> result = categoryService.getAll(pageable);

        assertEquals(1, result.size());
        assertEquals(CATEGORY_1_NAME, result.get(0).getName());
    }

    @Test
    @DisplayName("Get category by ID")
    void getCategoryById_WithValidId_ShouldReturnCategory() {
        Long categoryId = CATEGORY_ID;
        Category category = new Category();
        category.setId(categoryId);
        category.setName(SPECIFIC_CATEGORY_NAME);

        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setName(SPECIFIC_CATEGORY_NAME);

        when(categoryRepository.findById(categoryId)).thenReturn(java.util.Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);

        CategoryResponseDto result = categoryService.getById(categoryId);

        assertEquals(SPECIFIC_CATEGORY_NAME, result.getName());
    }

}
