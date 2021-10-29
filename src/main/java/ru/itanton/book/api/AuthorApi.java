package ru.itanton.book.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itanton.book.dao.AuthorDao;
import ru.itanton.book.model.Author;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.executable.ValidateOnExecution;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
@Validated
public class AuthorApi {
    private final AuthorDao authorDao;

    @Operation(summary = "Получить страницу авторов")
    @GetMapping
    public Page<Author> getAll(@Parameter(description = "Номер страницы")
                               @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                               @Parameter(description = "Количество элементов на странице")
                               @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        return authorDao.findAll(PageRequest.of(page, size));
    }

    @Operation(summary = "Получить автора по id")
    @GetMapping("{id}")
    public Author get(@PathVariable("id") Integer id) {
        return authorDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Author with id " + id + " not found"));
    }

    @Operation(summary = "Сохранить автора")
    @PostMapping
    public Author save(@Valid @RequestBody Author author) {
        if (author.getId() != null) {
            throw new RuntimeException("Id is not null!");
        }
        return authorDao.save(author);
    }

    @Operation(summary = "Обновить автора")
    @PutMapping
    public Author update(@Valid @RequestBody Author author) {
        if (author.getId() == null) {
            throw new RuntimeException("Id is null!");
        }
        return authorDao.save(author);
    }

    @Operation(summary = "Удалить автора")
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        authorDao.deleteById(id);
    }
}
