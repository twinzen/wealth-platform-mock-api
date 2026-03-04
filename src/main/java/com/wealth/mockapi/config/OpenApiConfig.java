package com.wealth.mockapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Wealth Platform Mock API")
                        .description("Mock REST API for wealth management platform testing – " +
                                "covers portfolio holdings, stock quotes, trade orders, and transactions.")
                        .version("v1.0.0")
                        .contact(new Contact().name("Wealth Platform Team")))
                .servers(List.of(new Server().url("http://localhost:8080").description("Local")));
    }
}
