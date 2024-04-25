package ua.chernonog.onlinebookstore.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ua.chernonog.onlinebookstore.dto.request.book.BookSearchParametersDto;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.repository.SpecificationBuilder;
import ua.chernonog.onlinebookstore.repository.SpecificationProviderManager;
import ua.chernonog.onlinebookstore.util.BookConstants;
import ua.chernonog.onlinebookstore.util.BookUtils;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto bookSearchParametersDto) {
        Specification<Book> spec = Specification.where(null);
        spec = buildSpecification(spec,
                bookSearchParametersDto.getAuthors(), BookConstants.AUTHOR);
        spec = buildSpecification(spec,
                bookSearchParametersDto.getTitles(), BookConstants.TITLE);
        return spec;
    }

    private Specification<Book> buildSpecification(
            Specification<Book> spec,
            String[] parameters, String key
    ) {
        if (BookUtils.isNullOrEmpty(parameters)) {
            return spec;
        }
        return spec.and(bookSpecificationProviderManager
                .getSpecificationProvider(key)
                .getSpecification(parameters));
    }
}
