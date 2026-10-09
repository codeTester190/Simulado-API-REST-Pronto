package com.api.logistica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações globais de documentação da OpenAPI
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Simulado Prova")
                        .version("1.0.0")
                        .description("Api funciona como um simulado para a prova de API REST"
                )
                );
    }

}
