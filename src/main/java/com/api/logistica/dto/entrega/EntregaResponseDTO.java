package com.api.logistica.dto.entrega;

import com.api.logistica.entity.enums.StatusEntrega;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representação publica de uma entrega retornada pela API
 *
 * @param id identificador unico
 * @param pedidoId identificador unico do pedido
 * @param motoristaId identificador unico do motorista
 * @param status status da entrega
 */

@Schema(description = "Dados de uma entrega retornada pela API")
public record EntregaResponseDTO(
        @Schema(description = "identificador unico", example = "1")
        Long id,

        @Schema(description = "identificador unico de pedido", example = "2")
        Long pedidoId,

        @Schema(description = "identificador unico de motorista", example = "3")
        Long motoristaId,

        @Schema(description = "Status da entrega", example = "EM_ROTA")
        StatusEntrega status
) {}
