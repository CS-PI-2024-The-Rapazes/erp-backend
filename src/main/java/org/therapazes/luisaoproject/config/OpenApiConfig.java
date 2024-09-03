package org.therapazes.luisaoproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Snacktime API")
                        .version("0.0.1")
                        .description("""
                                API para o projeto Snacktime, desenvolvido por Therapazes
                                , como forma de avaliação para as disciplinas de
                                 Construção de software e Projeto Integrador II."""));
    }
}