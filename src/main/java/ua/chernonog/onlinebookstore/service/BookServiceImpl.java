package ua.chernonog.onlinebookstore.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.request.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.BookDto;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.exception.EntityNotFoundException;
import ua.chernonog.onlinebookstore.mapper.BookMapper;
import ua.chernonog.onlinebookstore.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        Book newBook = bookMapper.toModel(bookDto);
        return bookMapper.toDto(bookRepository.save(newBook));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findBookById(Long id) {
        Optional<Book> bookFromDB = bookRepository.findById(id);

        return bookMapper.toDto(bookFromDB.orElseThrow(() ->
                new EntityNotFoundException("Can`t find book with id " + id + " in DB")));

    }
}
