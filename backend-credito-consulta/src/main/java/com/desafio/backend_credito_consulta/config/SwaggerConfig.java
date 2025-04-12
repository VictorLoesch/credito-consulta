package com.desafio.backend_credito_consulta.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Consulta de Créditos")
                        .version("v1")
                        .description("API REST para consulta e gerenciamento de créditos")
                        .contact(new Contact()
                                .name("Victor")
                                .email("victorsuto10@hotmail.com")));
    }

}
