package ua.chernonog.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.chernonog.onlinebookstore.dto.request.BookSearchParametersDto;
import ua.chernonog.onlinebookstore.dto.request.CreateBookRequestDto;
import ua.chernonog.onlinebookstore.dto.response.BookDto;
import ua.chernonog.onlinebookstore.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Book management", description = "Endpoints for managing books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Get all books")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Create a new book")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by Id", description = "Get a book by Id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by Id", description = "Delete a book by Id")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book by Id", description = "Update a book by Id")
    public BookDto update(@PathVariable Long id,
                          @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.updateById(id, requestDto);
    }

    @GetMapping("/search")
    @Operation(summary = "Search books by params", description = "Search books by params")
    public List<BookDto> searchBooks(BookSearchParametersDto searchParameters, Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }
}
