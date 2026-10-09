package com.api.logistica.dto.erro;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Estrutura padronizada para o retorno de erros da API
 *
 * @param status status código retornado pela requisição realida
 * @param erro Motivo de ter gerado erro
 * @param mensagem mensagem detalhando o erro
 * @param caminho caminho da URI do endpoint onde ocorreu a falha
 * @param timestamp data e hora do erro realizado
 */
@Schema(description = "DAdos utilizados para capturar um erro e tratar")
public record ErroResponse(
        @Schema(description = "Código de status HTTP", example = "200")
        int status,

        @Schema(description = "Identificador do tipo do erro", example = "BAD_REQUEST")
        String erro,

        @Schema(description = "Mensagem explicativa sobre o erro", example = "O campo 'nome' é obrigatorio")
        String mensagem,

        @Schema(description = "Caminho da URI do endpoint requisitado", example = "/v1/api/cliente")
        String caminho,

        @Schema(description = "Data e hora", example = "2026-10-02T10:42:22")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp
) {}
