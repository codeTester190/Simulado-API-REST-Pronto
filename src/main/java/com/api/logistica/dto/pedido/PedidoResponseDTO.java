package com.api.logistica.dto.pedido;

import com.api.logistica.entity.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * Representação publica de um pedido retornado pela API
 *
 * @param id identificador unico
 * @param clienteId identificador unico do cliente
 * @param volumeM3 volume por metro cubico
 * @param pesoKg peso em kilo
 * @param status status do pedido
 */

@Schema(description = "Dados de um pedido retornados pela API")
public record PedidoResponseDTO(
        @Schema(description = "Identificador Unico", example = "1")
        Long id,

        @Schema(description = "Identificador Unico do cliente", example = "1")
        Long clienteId,

        @Schema(description = "volume por metro cubico", example = "100")
        BigDecimal volumeM3,

        @Schema(description = "Peso em kilos", example = "100")
        BigDecimal pesoKg,

        @Schema(description = "Status", example = "CANCELADO")
        StatusPedido status
) {}
