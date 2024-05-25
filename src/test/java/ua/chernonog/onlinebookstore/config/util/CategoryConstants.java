package ua.chernonog.onlinebookstore.config.util;

public class CategoryConstants {
    public static final String CATEGORIES_BASE_URL = "/categories";
    public static final String CATEGORY_BY_ID_URL = CATEGORIES_BASE_URL + "/{id}";
    public static final String BOOKS_BY_CATEGORY_URL = CATEGORY_BY_ID_URL + "/books";
    public static final String CATEGORY_NAME = "Science Fiction";
    public static final String CATEGORY_DESCRIPTION = "Books that deal with imaginative concepts.";
    public static final String UPDATED_CATEGORY_NAME = "Updated Category";
    public static final String UPDATED_CATEGORY_DESCRIPTION = "Updated description";

    public static final String NEW_CATEGORY_NAME = "New Category";
    public static final String NEW_CATEGORY_DESCRIPTION = "Description of new category";
    public static final String CATEGORY_1_NAME = "Category 1";
    public static final String SPECIFIC_CATEGORY_NAME = "Specific Category";
    public static final String OLD_CATEGORY_NAME = "Old Category";
    public static final String OLD_CATEGORY_DESCRIPTION = "Old Description";

    public static final String NAME_JSON_PATH = "$.name";

    public static final long CATEGORY_ID = 1L;
}
