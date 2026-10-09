package com.api.logistica.dto.motorista;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representação pública de um produto retornado pela API
 *
 * @param id identificador unico
 * @param nome nome do motorista
 * @param cnh cnh do motorista
 * @param veiculo veiculo do motorista
 * @param cidadeBase cidade base do motorista
 */
@Schema(description = "Dados de um motorista retornados pela API")
public record MotoristaResponseDTO(
        @Schema(description = "Identificador unico", example = "1")
        Long id,

        @Schema(description = "Nome do motorista", example = "Pedro")
        String nome,

        @Schema(description = "Cnh do motorista", example = "111.111.111-11")
        String cnh,

        @Schema(description = "Veiculo do motorista", example = "Caminhão")
        String veiculo,

        @Schema(description = "Cidade base do motorista", example = "Jaraguá do Sul")
        String cidadeBase
) {}
