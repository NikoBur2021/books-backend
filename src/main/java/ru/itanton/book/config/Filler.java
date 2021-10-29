package ru.itanton.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itanton.book.dao.AuthorDao;
import ru.itanton.book.dao.BookDao;
import ru.itanton.book.model.Author;
import ru.itanton.book.model.Book;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Filler {

    private final BookDao bookDao;
    private final AuthorDao authorDao;

    @PostConstruct
    private void fill() {
        Author author1 = authorDao.save(Author.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .build()
        );
        Author author2 = authorDao.save(Author.builder()
                .firstName("Petr")
                .lastName("Petrov")
                .build()
        );
        Author author3 = authorDao.save(Author.builder()
                .firstName("Sergey")
                .lastName("Sergeev")
                .build()
        );
        Author author4 = authorDao.save(Author.builder()
                .firstName("Alexandr")
                .lastName("Alexandrov")
                .build()
        );
        Author author5 = authorDao.save(Author.builder()
                .firstName("Andrey")
                .lastName("Andreev")
                .build()
        );

        bookDao.saveAll(
                List.of(
                    Book.builder()
                            .author(author1)
                            .name("Name of book 1")
                            .price(BigDecimal.valueOf(10.5))
                            .storeCount(4)
                            .build(),
                    Book.builder()
                            .author(author1)
                            .name("Name of book 2")
                            .price(BigDecimal.valueOf(9.99))
                            .storeCount(14)
                            .build(),
                    Book.builder()
                            .author(author1)
                            .name("Name of book 3")
                            .price(BigDecimal.valueOf(50))
                            .storeCount(0)
                            .build(),
                    Book.builder()
                            .author(author1)
                            .name("Name of book 4")
                            .price(BigDecimal.valueOf(1.99))
                            .storeCount(7)
                            .build(),
                    Book.builder()
                            .author(author2)
                            .name("Name of book 5")
                            .price(BigDecimal.valueOf(17.5))
                            .storeCount(33)
                            .build(),
                    Book.builder()
                            .author(author2)
                            .name("Name of book 6")
                            .price(BigDecimal.valueOf(10.5))
                            .storeCount(2)
                            .build(),
                    Book.builder()
                            .author(author2)
                            .name("Name of book 7")
                            .price(BigDecimal.valueOf(15))
                            .storeCount(60)
                            .build(),
                    Book.builder()
                            .author(author3)
                            .name("Name of book 8")
                            .price(BigDecimal.valueOf(12))
                            .storeCount(12)
                            .build(),
                    Book.builder()
                            .author(author3)
                            .name("Name of book 9")
                            .price(BigDecimal.valueOf(8.99))
                            .storeCount(25)
                            .build(),
                    Book.builder()
                            .author(author3)
                            .name("Name of book 10")
                            .price(BigDecimal.valueOf(9.99))
                            .storeCount(125)
                            .build(),
                    Book.builder()
                            .author(author4)
                            .name("Name of book 11")
                            .price(BigDecimal.valueOf(49.99))
                            .storeCount(7)
                            .build(),
                    Book.builder()
                            .author(author4)
                            .name("Name of book 12")
                            .price(BigDecimal.valueOf(20))
                            .storeCount(52)
                            .build(),
                    Book.builder()
                            .author(author4)
                            .name("Name of book 13")
                            .price(BigDecimal.valueOf(42))
                            .storeCount(62)
                            .build(),
                    Book.builder()
                            .author(author4)
                            .name("Name of book 14")
                            .price(BigDecimal.valueOf(7.5))
                            .storeCount(12)
                            .build()
                )
        );
    }
}
