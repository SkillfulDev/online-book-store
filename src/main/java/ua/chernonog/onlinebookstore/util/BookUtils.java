package ua.chernonog.onlinebookstore.util;

import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import ua.chernonog.onlinebookstore.entity.Book;

public class BookUtils {
    public static boolean isNullOrEmpty(String[] array) {
        return array == null || array.length == 0;
    }

    public static Specification<Book> getBookSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get(BookConstants.AUTHOR)
                        .in(Arrays.stream(params).toArray());
    }
}
