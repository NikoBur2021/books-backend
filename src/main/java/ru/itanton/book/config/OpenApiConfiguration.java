package ru.itanton.book.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Swagger
 *
 * @author aitunin
 */
@Configuration
public class OpenApiConfiguration {

    private static final String API_VERSION = "1";
    private static final String TITLE = "Книжный магазин";
    private static final String DESCRIPTION = "Книжный магазин";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title(TITLE)
                        .version(API_VERSION)
                        .description(DESCRIPTION));
    }

}
