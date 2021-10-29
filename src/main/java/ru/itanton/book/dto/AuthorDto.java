package ru.itanton.book.dto;

import lombok.Builder;

@Builder
public class AuthorDto {
    Integer id;
    String firstName;
    String lastName;
    BookDto bookDTO;
}
