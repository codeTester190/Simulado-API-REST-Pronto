package com.api.logistica.dto.relatorio;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Representação pública retornada pela API to total de entregas por motorista
 * @param motoristaId identificador unico do motorista
 * @param nomeMotorista nome do motorista
 * @param totalEntregas total de entregas por motorista
 */
@Schema(description = "Dados de entregas por motorista retornados pela API")
public record EntregasPorMotoristaDTO(
        @Schema(description = "identificador unico do motorista", example = "1")
        Long motoristaId,
        @Schema(description = "Nome do motorista", example = "João")
        String nomeMotorista,
        @Schema(description = "Total de entregas por motorista", example = "10")
        long totalEntregas
) {}
