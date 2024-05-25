package ua.chernonog.onlinebookstore.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.BOOKS_BY_CATEGORY_URL;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.CATEGORIES_BASE_URL;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.CATEGORY_BY_ID_URL;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.CATEGORY_DESCRIPTION;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.CATEGORY_ID;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.CATEGORY_NAME;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.NAME_JSON_PATH;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.JSON_PATH_FIRST_ITEM_EXISTS;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.chernonog.onlinebookstore.dto.request.category.CreateCategoryDto;
import ua.chernonog.onlinebookstore.dto.response.book.BookDtoWithoutCategoryIds;
import ua.chernonog.onlinebookstore.dto.response.category.CategoryResponseDto;
import ua.chernonog.onlinebookstore.service.CategoryService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerTest {
    private static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private CategoryService categoryService;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext context) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Save a new category by admin")
    void save_WithValidCategory_ShouldReturnSavedCategory() throws Exception {
        CreateCategoryDto createCategoryDto = new CreateCategoryDto();
        createCategoryDto.setName(CATEGORY_NAME);
        createCategoryDto.setDescription(CATEGORY_DESCRIPTION);

        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(CATEGORY_ID);
        responseDto.setName(createCategoryDto.getName());
        responseDto.setDescription(createCategoryDto.getDescription());

        when(categoryService.save(any(CreateCategoryDto.class))).thenReturn(responseDto);

        mockMvc.perform(post(CATEGORIES_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCategoryDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath(NAME_JSON_PATH).value(CATEGORY_NAME));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Retrieve all categories")
    void getAll_WithUserRole_ShouldReturnAllCategories() throws Exception {
        List<CategoryResponseDto> categories = List.of(new CategoryResponseDto());
        when(categoryService.getAll(any())).thenReturn(categories);

        mockMvc.perform(get(CATEGORIES_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_FIRST_ITEM_EXISTS).exists());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Delete a category by admin")
    void deleteCategory_WithValidId_ShouldReturnOkStatus() throws Exception {
        Long categoryId = CATEGORY_ID;
        doNothing().when(categoryService).deleteById(categoryId);

        mockMvc.perform(delete(CATEGORY_BY_ID_URL,
                        categoryId).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Get books by category")
    void getBooksByCategory_WithValidCategoryId_ShouldReturnBooks() throws Exception {
        List<BookDtoWithoutCategoryIds> books = List.of(new BookDtoWithoutCategoryIds());
        when(categoryService.getBookByCategory(eq(CATEGORY_ID),
                any())).thenReturn(books);

        mockMvc.perform(get(BOOKS_BY_CATEGORY_URL, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_FIRST_ITEM_EXISTS).exists());
    }
}
