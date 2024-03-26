package ua.chernonog.onlinebookstore.service;

import ua.chernonog.onlinebookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
}
