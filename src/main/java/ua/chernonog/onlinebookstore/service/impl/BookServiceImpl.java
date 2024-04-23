package ua.chernonog.onlinebookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.request.book.BookSearchParametersDto;
import ua.chernonog.onlinebookstore.dto.request.book.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.book.BookDtoWithoutCategoryIds;
import ua.chernonog.onlinebookstore.dto.response.book.BookResponseDto;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.exception.EntityNotFoundException;
import ua.chernonog.onlinebookstore.mapper.BookMapper;
import ua.chernonog.onlinebookstore.repository.SpecificationBuilder;
import ua.chernonog.onlinebookstore.repository.book.BookRepository;
import ua.chernonog.onlinebookstore.service.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final SpecificationBuilder<Book> specificationBuilder;

    @Override
    public BookResponseDto save(CreateBookRequestDto bookDto) {
        Book newBook = bookMapper.toModel(bookDto);
        return bookMapper.toDto(bookRepository.save(newBook));
    }

    @Override
    public List<BookResponseDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookResponseDto getById(Long id) {
        return bookMapper.toDto(getBookIfExist(id));
    }

    @Override
    public BookResponseDto updateById(Long id, CreateBookRequestDto requestDto) {
        Book bookFromDb = getBookIfExist(id);
        Book updatedBook = updateBookFields(bookFromDb, requestDto);

        return bookMapper.toDto(bookRepository.save(updatedBook));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(
                getBookIfExist(id).getId());
    }

    @Override
    public List<BookResponseDto> search(
            BookSearchParametersDto searchParameters,
            Pageable pageable
    ) {
        Specification<Book> bookSpecification = specificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification, pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBookByCategory(Long id, Pageable pageable) {
        return bookRepository.findBooksByCategoryId(id, pageable)
                .stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    private Book getBookIfExist(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can`t find book with id " + id + " in DB"));
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
