package com.api.logistica.dto.historico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Dados utilizados para gerar um historico
 *
 * @param descricao descrição do historico gerado
 */
@Schema(description = "Dados utilizados para gerar um historico")
public record HistoricoRequestDTO(
        @Schema(description = "Descrição da entrega")
        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 200, message = "A descrição deve ter no máximo 200 caracteres")
        String descricao
) {}
