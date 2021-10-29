package ru.itanton.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itanton.book.model.Author;
import ru.itanton.book.model.Book;

public interface AuthorDao extends JpaRepository<Author, Integer> {
}
