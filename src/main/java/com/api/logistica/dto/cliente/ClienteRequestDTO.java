package com.api.logistica.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Dados necessarios para cadastrar um novo cliente
 *
 * @param nome nome do cliente
 * @param cpfCnpj cpf ou cnpj do cliente
 * @param endereco endereço do cliente
 * @param cidade cidade do cliente
 * @param estado estado do cliente
 */

@Schema(description = "Dados utilizados para cadastrar um cliente")
public record ClienteRequestDTO(
        @Schema(description = "Nome do cliente", example = "Arthur")
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
        String nome,

        @Schema(description = "Cpf ou cnpj do cliente", example = "000.000.000-00")
        @NotBlank(message = "O CPF/CNPJ é obrigatório")
        @Pattern(regexp = "\\d{11}|\\d{14}", message = "Informe um CPF (11 dígitos) ou CNPJ (14 dígitos), somente números")
        String cpfCnpj,

        @Schema(description = "Endereço do cliente", example = "Rua das Flores")
        @NotBlank(message = "O endereço é obrigatório")
        @Size(min = 3, max = 150, message = "O endereço deve ter entre 3 e 150 caracteres")
        String endereco,

        @Schema(description = "Cidade do cliente", example = "Jaraguá do Sul")
        @NotBlank(message = "A cidade é obrigatória")
        @Size(min = 2, max = 90, message = "A cidade deve ter entre 2 e 90 caracteres")
        String cidade,

        @Schema(description = "Estado do cliente", example = "SC")
        @NotBlank(message = "O estado (UF) é obrigatório")
        @Size(min = 2, max = 2, message = "O estado deve ser a sigla UF com 2 letras")
        String estado
) {}
