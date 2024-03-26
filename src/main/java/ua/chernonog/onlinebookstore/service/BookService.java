package ua.chernonog.onlinebookstore.service;

import java.util.List;
import ua.chernonog.onlinebookstore.entity.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
