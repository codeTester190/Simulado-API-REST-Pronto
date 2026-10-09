package com.api.logistica.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representação publica de um cliente retornado pela API
 *
 * @param id identificador unico
 * @param nome nome do cliente
 * @param cpfCnpj cpf ou cnpj do cliente
 * @param endereco endereço do cliente
 * @param cidade cidade do cliente
 * @param estado estado do cliente
 */
@Schema(description = "Dados de cliente retornados da API")
public record ClienteResponseDTO(
        @Schema(description = "Identificador unico", example = "1")
        Long id,

        @Schema(description = "Nome do cliente", example = "Daniel")
        String nome,

        @Schema(description = "Cpf ou cnpj do cliente", example = "111.111.111-11")
        String cpfCnpj,

        @Schema(description = "Endereço do cliente", example = "Rua das flores 123")
        String endereco,

        @Schema(description = "Cidade do cliente", example = "Rio de Janeiro")
        String cidade,

        @Schema(description = "Estado cliente", example = "RJ")
        String estado
) {}