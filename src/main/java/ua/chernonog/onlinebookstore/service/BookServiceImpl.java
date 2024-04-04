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
        if (bookFromDB.isPresent()) {
            return bookMapper.toDto(bookFromDB.get());
        }
        throw new EntityNotFoundException("Can`t find book with id " + id + " in DB");
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id)
                .map(existingBook -> updateBookFields(existingBook, requestDto))
                .orElseThrow(() -> new EntityNotFoundException("Can't update book with id " + id));
        return bookMapper.toDto(bookRepository.save(book));
    }

    private Book updateBookFields(Book book, CreateBookRequestDto requestDto) {
        book.setIsbn(requestDto.getIsbn());
        book.setAuthor(requestDto.getAuthor());
        book.setPrice(requestDto.getPrice());
        book.setDescription(requestDto.getDescription());
        book.setCoverImage(requestDto.getCoverImage());
        book.setTitle(requestDto.getTitle());
        return book;
    }
}
