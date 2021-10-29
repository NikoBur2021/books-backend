package ru.itanton.book.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itanton.book.dto.ExceptionDto;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiAdvice  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ExceptionDto catchEx(Exception e, WebRequest request) {
        return ExceptionDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto catchExR(EntityNotFoundException e, WebRequest request) {
        return ExceptionDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto catchExR(RuntimeException e, WebRequest request) {
        return ExceptionDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build();
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + " " + e.getDefaultMessage())
                .collect(Collectors.toList());

        ExceptionDto.ExceptionDtoBuilder builder = ExceptionDto.builder().timestamp(LocalDateTime.now()).path(request.getDescription(false));
        if (message.size() == 1) {
            builder.message(message.get(0));
        } else {
            builder.messages(message);
        }

        return ResponseEntity.of(Optional.of(builder.build()));
    }
}
