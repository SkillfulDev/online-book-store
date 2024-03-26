package ua.chernonog.onlinebookstore.repository;

import java.util.List;
import ua.chernonog.onlinebookstore.entity.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
