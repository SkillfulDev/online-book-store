package ua.chernonog.onlinebookstore.config.util;

import java.math.BigDecimal;

public class BookConstants {
    public static final String BOOKS_BASE_URL = "/books";
    public static final String BOOKS_BASE_URL_WITH_PATH_ID = "/books/{id}";
    public static final String BOOKS_SEARCH_URL = BOOKS_BASE_URL + "/search";
    public static final String JSON_PATH_FIRST_ITEM_EXISTS = "$[0]";
    public static final String TITLE = "Example Book";
    public static final String AUTHOR = "Author";
    public static final String ISBN = "978-3-16-148410-0";
    public static final BigDecimal PRICE = new BigDecimal("19.99");
    public static final String DESCRIPTION = "A sample book for testing.";
    public static final String COVER_IMAGE_URL = "http://example.com/cover.jpg";

    public static final String BOOK_TITLE_NEURAL_NETWORKS = "Next-Generation Neural Networks";
    public static final String BOOK_AUTHOR_JANE_DOE = "Jane Doe";

    public static final long VALID_BOOK_ID = 1L;
    public static final long VALID_CATEGORY_ID = 1L;

    public static final String NEW_BOOK_TITLE = "New Book";
    public static final String NEW_BOOK_AUTHOR = "New Author";
    public static final String NEW_BOOK_ISBN = "123-4-56-789012-3";
    public static final BigDecimal NEW_BOOK_PRICE = new BigDecimal("25.00");
    public static final String NEW_BOOK_DESCRIPTION = "A new book description";
    public static final String NEW_BOOK_COVER_IMAGE = "http://example.com/newbook.jpg";

    public static final String UPDATED_BOOK_TITLE = "Updated Book";
    public static final String SET_BOOK_TITLE = "First Book";
    public static final String UPDATED_BOOK_AUTHOR = "Updated Author";
    public static final String UPDATED_BOOK_ISBN = "321-4-56-789012-3";
    public static final BigDecimal UPDATED_BOOK_PRICE = new BigDecimal("35.00");
    public static final String UPDATED_BOOK_DESCRIPTION = "Updated description";
    public static final String UPDATED_BOOK_COVER_IMAGE = "http://example.com/updatedbook.jpg";

    public static final String OLD_BOOK_TITLE = "Old Book";

    public static final String EXAMPLE_BOOK_AUTHOR = "Author Name";
    public static final String EXAMPLE_BOOK_ISBN = "978-3-16-148410-0";
    public static final BigDecimal EXAMPLE_BOOK_PRICE = new BigDecimal("19.99");
    public static final String EXAMPLE_BOOK_DESCRIPTION =
            "A detailed description of the example book.";
}
