package ua.chernonog.onlinebookstore.dto.request.book;

import lombok.Data;

@Data
public class BookSearchParametersDto {
    private String[] titles;
    private String[] authors;
}
