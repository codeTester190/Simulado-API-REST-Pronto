package com.api.logistica.mapper;

import com.api.logistica.dto.cliente.ClienteRequestDTO;
import com.api.logistica.dto.cliente.ClienteResponseDTO;
import com.api.logistica.entity.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {
    /**
     * Converte dados de criação para uma entidade Cliente.
     * @param dto request dados recebidos para criação
     * @return entidade cliente
     */

    // aqui estou transformando uma dto em um entidade
    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpfCnpj(dto.cpfCnpj());
        cliente.setEndereco(dto.endereco());
        cliente.setCidade(dto.cidade());
        cliente.setEstado(dto.estado().toUpperCase());
        return cliente;
    }

    /**
     * Converte uma entidade Produto para um DTO de resposta
     * @param cliente entidade persistida
     * @return representação pública do cliente
     */
    // aqui estou transformando uma entidade para um dto (RESPONSE)
    public ClienteResponseDTO toResponse(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getCpfCnpj(),
                cliente.getEndereco(), cliente.getCidade(), cliente.getEstado());
    }

    /**
     * Converte uma lista de Entidades para uma lista de DTOs de resposta
     * @param clientes entidade persistida
     * @return representação publica de todos os clientes retornados
     */
    public List<ClienteResponseDTO> toResponseList(List<Cliente> clientes) {
        return clientes.stream().map(this::toResponse).toList();
    }
}
