package com.api.logistica.dto.entrega;

import com.api.logistica.entity.enums.StatusEntrega;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Estrutura padronizada para o retorno de status de entrega da API
 * @param status status da entrega
 */
@Schema(description = "Dados para a troca de entregas pela API")
public record EntregaStatusDTO(
        @Schema(description = "Status da entrega", example = "ENTREGUE")
        @NotNull(message = "O status é obrigatório (ENTREGUE ou ATRASADA)")
        StatusEntrega status
) {}
