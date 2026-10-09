package com.api.logistica.dto.entrega;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Dados necessarios para realizar o cadastro de uma nova entrega
 *
 * @param pedidoId identificador unico do pedido
 * @param motoristaId identificador unico de motorista
 */
@Schema(description = "Dados utilizados  para cadastrar uma entrega")
public record EntregaRequestDTO(

        @Schema(description = "Identificador unico do pedido", example = "1")
        @NotNull(message = "O pedidoId é obrigatório")
        Long pedidoId,

        @Schema(description = "Identificador unico de motorista", example = "1")
        @NotNull(message = "O motoristaId é obrigatório")
        Long motoristaId
) {}
