package ua.chernonog.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.onlinebookstore.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
