package ua.chernonog.onlinebookstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.EXAMPLE_BOOK_AUTHOR;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.EXAMPLE_BOOK_DESCRIPTION;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.EXAMPLE_BOOK_ISBN;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.EXAMPLE_BOOK_PRICE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.NEW_BOOK_AUTHOR;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.NEW_BOOK_COVER_IMAGE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.NEW_BOOK_DESCRIPTION;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.NEW_BOOK_ISBN;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.NEW_BOOK_PRICE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.NEW_BOOK_TITLE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.OLD_BOOK_TITLE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.SET_BOOK_TITLE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.TITLE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.UPDATED_BOOK_AUTHOR;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.UPDATED_BOOK_COVER_IMAGE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.UPDATED_BOOK_DESCRIPTION;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.UPDATED_BOOK_ISBN;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.UPDATED_BOOK_PRICE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.UPDATED_BOOK_TITLE;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.VALID_BOOK_ID;
import static ua.chernonog.onlinebookstore.config.util.CategoryConstants.CATEGORY_ID;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.DEFAULT_PAGE_NUMBER;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.DEFAULT_PAGE_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ua.chernonog.onlinebookstore.dto.request.book.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.book.BookDtoWithoutCategoryIds;
import ua.chernonog.onlinebookstore.dto.response.book.BookResponseDto;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.mapper.BookMapper;
import ua.chernonog.onlinebookstore.repository.book.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookMapper bookMapper;

    @Test
    @DisplayName("Save a new book and return the saved book details")
    void save_WithValidBook_ShouldReturnSavedBook() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle(NEW_BOOK_TITLE);
        requestDto.setAuthor(NEW_BOOK_AUTHOR);
        requestDto.setIsbn(NEW_BOOK_ISBN);
        requestDto.setPrice(NEW_BOOK_PRICE);
        requestDto.setDescription(NEW_BOOK_DESCRIPTION);
        requestDto.setCoverImage(NEW_BOOK_COVER_IMAGE);

        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setTitle(requestDto.getTitle());
        responseDto.setAuthor(requestDto.getAuthor());
        responseDto.setIsbn(requestDto.getIsbn());
        responseDto.setPrice(requestDto.getPrice());
        responseDto.setDescription(requestDto.getDescription());
        responseDto.setCoverImage(requestDto.getCoverImage());

        Book newBook = new Book();

        Mockito.when(bookMapper.toModel(requestDto)).thenReturn(newBook);
        Mockito.when(bookRepository.save(newBook)).thenReturn(newBook);
        Mockito.when(bookMapper.toDto(newBook)).thenReturn(responseDto);

        BookResponseDto savedBook = bookService.save(requestDto);

        assertEquals(NEW_BOOK_TITLE, savedBook.getTitle());
        assertEquals(NEW_BOOK_COVER_IMAGE, savedBook.getCoverImage());
    }

    @Test
    @DisplayName("Retrieve all books with pagination")
    void getAll_ShouldReturnAllBooks() {
        Book book = new Book();
        book.setId(VALID_BOOK_ID);
        book.setTitle(SET_BOOK_TITLE);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Page<Book> page = new PageImpl<>(bookList);

        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setTitle(SET_BOOK_TITLE);

        Pageable pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);

        Mockito.when(bookRepository.findAll(pageable)).thenReturn(page);
        Mockito.when(bookMapper.toDto(book)).thenReturn(responseDto);

        List<BookResponseDto> books = bookService.getAll(pageable);

        assertEquals(1, books.size());
        assertEquals(SET_BOOK_TITLE, books.get(0).getTitle());
    }

    @Test
    @DisplayName("Get a book by id")
    void getBook_WithValidBook_ShouldReturnValidBook() {
        Book book = new Book();
        book.setId(VALID_BOOK_ID);
        book.setTitle(TITLE);
        book.setAuthor(EXAMPLE_BOOK_AUTHOR);
        book.setIsbn(EXAMPLE_BOOK_ISBN);
        book.setPrice(EXAMPLE_BOOK_PRICE);
        book.setDescription(EXAMPLE_BOOK_DESCRIPTION);

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        BookResponseDto dto = new BookResponseDto();
        dto.setTitle(book.getTitle());
        Mockito.when(bookMapper.toDto(book)).thenReturn(dto);

        BookResponseDto responseDto = bookService.getById(book.getId());

        assertEquals(TITLE, responseDto.getTitle());
    }

    @Test
    @DisplayName("Update a book and return the updated details")
    void updateById_WithExistingBook_ShouldUpdateAndReturnUpdatedBook() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle(UPDATED_BOOK_TITLE);
        requestDto.setAuthor(UPDATED_BOOK_AUTHOR);
        requestDto.setIsbn(UPDATED_BOOK_ISBN);
        requestDto.setPrice(UPDATED_BOOK_PRICE);
        requestDto.setDescription(UPDATED_BOOK_DESCRIPTION);
        requestDto.setCoverImage(UPDATED_BOOK_COVER_IMAGE);

        Long bookId = VALID_BOOK_ID;
        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle(OLD_BOOK_TITLE);

        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setTitle(requestDto.getTitle());
        responseDto.setAuthor(requestDto.getAuthor());
        responseDto.setIsbn(requestDto.getIsbn());
        responseDto.setPrice(requestDto.getPrice());
        responseDto.setDescription(requestDto.getDescription());
        responseDto.setCoverImage(requestDto.getCoverImage());

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        Mockito.when(bookRepository.save(existingBook)).thenReturn(existingBook);
        Mockito.when(bookMapper.toDto(existingBook)).thenReturn(responseDto);

        BookResponseDto updatedBook = bookService.updateById(bookId, requestDto);

        assertEquals(UPDATED_BOOK_TITLE, updatedBook.getTitle());
        assertEquals(UPDATED_BOOK_AUTHOR, updatedBook.getAuthor());
        assertEquals(UPDATED_BOOK_COVER_IMAGE, updatedBook.getCoverImage());
    }

    @Test
    @DisplayName("Delete a book by ID")
    void deleteById_WithValidId_ShouldDeleteTheBook() {
        Long bookId = VALID_BOOK_ID;
        Book mockBook = new Book();
        mockBook.setId(bookId);

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        Mockito.doNothing().when(bookRepository).deleteById(bookId);

        bookService.deleteById(bookId);

        Mockito.verify(bookRepository).deleteById(bookId);
    }

    @Test
    @DisplayName("Retrieve books by category with pagination")
    void getBookByCategory_WithValidCategory_ShouldReturnBooks() {
        Long categoryId = CATEGORY_ID;
        Pageable pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        List<Book> bookList = Collections.singletonList(new Book());
        Page<Book> page = new PageImpl<>(bookList);

        Mockito.when(bookRepository.findBooksByCategoryId(categoryId, pageable)).thenReturn(page);
        Mockito.when(bookMapper.toDtoWithoutCategories(
                any(Book.class))).thenReturn(new BookDtoWithoutCategoryIds());

        List<BookDtoWithoutCategoryIds> results =
                bookService.getBookByCategory(categoryId, pageable);

        assertEquals(1, results.size());
    }
}
