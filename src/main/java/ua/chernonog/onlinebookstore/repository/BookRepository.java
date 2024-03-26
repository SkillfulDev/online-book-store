package ua.chernonog.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.onlinebookstore.entity.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
