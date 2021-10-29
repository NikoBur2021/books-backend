package ru.itanton.book.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.itanton.book.dao.BookDao;
import ru.itanton.book.model.Book;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookApi {

    private final BookDao bookDao;

    @Operation(summary = "Получить страницу книг")
    @GetMapping
    public Page<Book> getAll(@Parameter(description = "Номер страницы")
                             @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                             @Parameter(description = "Количество элементов на странице")
                             @RequestParam(name = "size", defaultValue = "15", required = false) Integer size) {
        return bookDao.findAll(PageRequest.of(page, size));
    }

    @Operation(summary = "Получить книгу по id")
    @GetMapping("{id}")
    public Book get(@PathVariable("id") Integer id) {
        return bookDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
    }

    @Operation(summary = "Сохранить книгу")
    @PostMapping
    public Book save(@Valid @RequestBody Book book) {
        if (book.getId() != null) {
            throw new RuntimeException("Id is not null!");
        }
        return bookDao.save(book);
    }

    @Operation(summary = "Обновить книгу")
    @PutMapping
    public Book update(@Valid @RequestBody Book book) {
        if (book.getId() == null) {
            throw new RuntimeException("Id is null!");
        }
        if (book.getAuthor() != null && book.getAuthor().getId() == null) {
            throw new RuntimeException("Author id is null!");
        }
        return bookDao.save(book);
    }

    @Operation(summary = "Удалить книгу")
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        bookDao.deleteById(id);
    }
}
