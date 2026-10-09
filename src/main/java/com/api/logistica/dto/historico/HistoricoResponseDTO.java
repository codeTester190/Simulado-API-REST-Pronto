package com.api.logistica.dto.historico;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Representação publica do historico retornado pela API
 *
 * @param id identificador unico
 * @param entregaId identificador unico da entrega
 * @param descricao descrição do historico gerado
 * @param dataHora data e hora do historico gerado
 */
@Schema(description = "Dados do historico retornado pela API")
public record HistoricoResponseDTO(
        @Schema(description = "Identificador unico", example = "1")
        Long id,

        @Schema(description = "Identificador unico de entrega", example = "1")
        Long entregaId,

        @Schema(description = "Descrição", example = "1")
        String descricao,

        @Schema(description = "Data e hora de quando o historico foi gerado", example = "10:00")
        LocalDateTime dataHora
) {}
