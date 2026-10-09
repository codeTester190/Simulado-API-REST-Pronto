package com.api.logistica.dto.motorista;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Dados necessarios para cadastrar um novo motorista
 * @param nome nome do motorista
 * @param cnh cnh do motorista
 * @param veiculo veiculo do motorista
 * @param cidadeBase cidade base
 */

@Schema(description = "Dados utilizados para cadastrar um novo motorista")
public record MotoristaRequestDTO(
        @Schema(description = "Nome do motorista", example = "Gustavo")
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Schema(description = "Cnh do motorista", example = "111.111.111-11")
        @NotBlank(message = "A CNH é obrigatória")
        @Pattern(regexp = "\\d{11}", message = "A CNH deve ter 11 dígitos, somente números")
        String cnh,

        @Schema(description = "Veiculo que o motorista vai utilizar", example = "Caminhão")
        @NotBlank(message = "O veículo é obrigatório")
        @Size(min = 2, max = 100, message = "O veículo deve ter entre 2 e 100 caracteres")
        String veiculo,

        @Schema(description = "Cidade base do motorista", example = "Jaraguá do Sul")
        @NotBlank(message = "A cidade base é obrigatória")
        @Size(min = 2, max = 90, message = "A cidade base deve ter entre 2 e 90 caracteres")
        String cidadeBase
) {}
