package ua.chernonog.onlinebookstore.repository.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.repository.BookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }
}
