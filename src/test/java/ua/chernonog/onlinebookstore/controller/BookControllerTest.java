package ua.chernonog.onlinebookstore.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.AUTHOR;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.BOOKS_BASE_URL;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.BOOKS_BASE_URL_WITH_PATH_ID;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.BOOKS_SEARCH_URL;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.COVER_IMAGE_URL;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.DESCRIPTION;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.ISBN;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.JSON_PATH_FIRST_ITEM_EXISTS;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.PRICE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.TITLE;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.chernonog.onlinebookstore.dto.request.book.BookSearchParametersDto;
import ua.chernonog.onlinebookstore.dto.request.book.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.book.BookResponseDto;
import ua.chernonog.onlinebookstore.service.BookService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    private static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private BookService bookService;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext context) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test retrieving all books")
    void getAllBooks_WithUserRole_ShouldReturnAllBooks() throws Exception {
        List<BookResponseDto> books = List.of(new BookResponseDto());
        when(bookService.getAll(any(Pageable.class))).thenReturn(books);

        mockMvc.perform(get(BOOKS_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_FIRST_ITEM_EXISTS).exists());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test searching books by parameters")
    void searchBooks_WithValidParameters_ShouldReturnBooks() throws Exception {
        List<BookResponseDto> books = List.of(new BookResponseDto());
        BookSearchParametersDto searchParameters = new BookSearchParametersDto();

        when(bookService.search(any(
                BookSearchParametersDto.class),
                any(Pageable.class))).thenReturn(books);

        String searchJson = objectMapper.writeValueAsString(searchParameters);

        mockMvc.perform(get(BOOKS_SEARCH_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(searchJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_FIRST_ITEM_EXISTS).exists());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("Test valid book creation by an admin")
    void createBook_WithValidDataAndAdminRole_ShouldCreateBook() throws Exception {
        CreateBookRequestDto bookDto = new CreateBookRequestDto();
        bookDto.setTitle(TITLE);
        bookDto.setAuthor(AUTHOR);
        bookDto.setIsbn(ISBN);
        bookDto.setPrice(PRICE);
        bookDto.setDescription(DESCRIPTION);
        bookDto.setCoverImage(COVER_IMAGE_URL);

        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle(bookDto.getTitle());
        responseDto.setAuthor(bookDto.getAuthor());
        responseDto.setIsbn(bookDto.getIsbn());
        responseDto.setPrice(bookDto.getPrice());
        responseDto.setDescription(bookDto.getDescription());
        responseDto.setCoverImage(bookDto.getCoverImage());

        when(bookService.save(any(CreateBookRequestDto.class))).thenReturn(responseDto);

        objectMapper = new ObjectMapper();
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(post(BOOKS_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(TITLE));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test book creation fails for non-admin user")
    void createBook_WithUserRole_ShouldFail() throws Exception {
        CreateBookRequestDto bookDto = new CreateBookRequestDto();
        bookDto.setTitle(TITLE);
        bookDto.setAuthor(AUTHOR);
        bookDto.setIsbn(ISBN);
        bookDto.setPrice(PRICE);
        bookDto.setDescription(DESCRIPTION);
        bookDto.setCoverImage(COVER_IMAGE_URL);

        objectMapper = new ObjectMapper();
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(post(BOOKS_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Test valid book deletion by an admin")
    void deleteBook_WithValidIdAndAdminRole_ShouldDeleteBook() throws Exception {
        Long bookId = 1L;

        doNothing().when(bookService).deleteById(bookId);

        mockMvc.perform(delete(BOOKS_BASE_URL_WITH_PATH_ID, bookId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Test book deletion fails for non-admin user")
    void deleteBook_WithUserRole_ShouldFail() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(delete("/books/{id}", bookId))
                .andExpect(status().isForbidden());
    }
}
