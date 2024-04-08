package ua.chernonog.onlinebookstore.repository.book.spec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.repository.SpecificationProvider;
import ua.chernonog.onlinebookstore.util.BookConstants;
import ua.chernonog.onlinebookstore.util.BookUtils;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return BookConstants.AUTHOR;
    }

    public Specification<Book> getSpecification(String[] params) {
        return BookUtils.getBookSpecification(params,BookConstants.AUTHOR);
    }
}
