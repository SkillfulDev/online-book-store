package ua.chernonog.onlinebookstore.dto.request;

import lombok.Data;

@Data
public class BookSearchParametersDto {
    private String[] titles;
    private String[] authors;
}
