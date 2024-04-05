package ua.chernonog.onlinebookstore.repository;

import org.springframework.data.jpa.domain.Specification;
import ua.chernonog.onlinebookstore.dto.request.BookSearchParametersDto;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParametersDto bookSearchParametersDto);
}
