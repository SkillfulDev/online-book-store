package ua.chernonog.onlinebookstore.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.BOOK_TITLE_NEURAL_NETWORKS;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.VALID_BOOK_ID;
import static ua.chernonog.onlinebookstore.config.util.BookConstants.VALID_CATEGORY_ID;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.DEFAULT_PAGE_NUMBER;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.DEFAULT_PAGE_SIZE;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.DELETE_BOOKS_SQL;
import static ua.chernonog.onlinebookstore.config.util.UtilConstants.INSERT_BOOKS_SQL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.repository.book.BookRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Find book by ID")
    @Sql(scripts = {
            DELETE_BOOKS_SQL,
            INSERT_BOOKS_SQL},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findBookById_WithValidId_ShouldReturnBook() {
        Book book = bookRepository.findById(VALID_BOOK_ID).orElse(null);
        assertTrue(book != null);
        assertEquals(BOOK_TITLE_NEURAL_NETWORKS, book.getTitle());
    }

    @Test
    @DisplayName("Find books by category ID with pagination")
    @Sql(scripts = {
            DELETE_BOOKS_SQL,
            INSERT_BOOKS_SQL},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findBooksByCategoryId_WithValidCategoryId_ShouldReturnBooks() {
        Page<Book> books = bookRepository.findBooksByCategoryId(VALID_CATEGORY_ID,
                PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
        assertEquals(2, books.getTotalElements());
        assertEquals(BOOK_TITLE_NEURAL_NETWORKS, books.getContent().get(0).getTitle());
    }
}
