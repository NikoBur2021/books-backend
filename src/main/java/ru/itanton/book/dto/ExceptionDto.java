package ru.itanton.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.util.Strftime;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDto {

    String message;
    List<String> messages;
    LocalDateTime timestamp;
    String path;

}
