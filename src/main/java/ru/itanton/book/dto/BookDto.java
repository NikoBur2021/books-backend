package ru.itanton.book.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class BookDto {
    Integer id;
    String name;
    BigDecimal price;
    Integer storeCount;
    AuthorDto author;
}
