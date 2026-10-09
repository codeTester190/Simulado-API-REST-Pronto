package com.api.logistica.dto.pedido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Dados necessarios para criar um novo pedido
 * @param clienteId identificador unico do cliente
 * @param volumeM3 volume por metro cubico
 * @param pesoKg peso em kilos
 */

@Schema(description = "Dados de utilizados para cadastrar um novo pedido")
public record PedidoRequestDTO(
        @Schema(description = "identificador unico do cliente")
        @NotNull(message = "O clienteId é obrigatório")
        @Positive(message = "O clienteId deve ser maior que zero")
        Long clienteId,

        @Schema(description = "volume por metro cubico")
        @NotNull(message = "O volume (m³) é obrigatório")
        @Positive(message = "O volume (m³) deve ser maior que zero")
        BigDecimal volumeM3,

        @Schema(description = "Peso em kilos")
        @NotNull(message = "O peso (kg) é obrigatório")
        @Positive(message = "O peso (kg) deve ser maior que zero")
        BigDecimal pesoKg
) {}
