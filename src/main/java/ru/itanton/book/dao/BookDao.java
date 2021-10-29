package ru.itanton.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itanton.book.model.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
}
