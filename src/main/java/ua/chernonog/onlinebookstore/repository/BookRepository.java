package ua.chernonog.onlinebookstore.repository;

import java.util.List;
import java.util.Optional;
import ua.chernonog.onlinebookstore.entity.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(Long id);
}
