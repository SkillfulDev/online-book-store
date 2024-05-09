package ua.chernonog.onlinebookstore.config.util;

public class UtilConstants {
    public static final String DELETE_BOOKS_SQL =
            "classpath:database/scripts/delete-values-from-book.sql";
    public static final String INSERT_BOOKS_SQL =
            "classpath:database/scripts/insert-values-into-book.sql";
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String JSON_PATH_FIRST_ITEM_EXISTS = "$[0]";
}
