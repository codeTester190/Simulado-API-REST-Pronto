package com.api.logistica.mapper;

import com.api.logistica.dto.motorista.MotoristaRequestDTO;
import com.api.logistica.dto.motorista.MotoristaResponseDTO;
import com.api.logistica.entity.Motorista;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MotoristaMapper {

    /**
     * Converte a entidade Motorista para uma entidade Motorista
     * @param dto request dados recebidos para criação
     * @return da entidade motorista
     */
    public Motorista toEntity(MotoristaRequestDTO dto) {
        Motorista motorista = new Motorista();
        motorista.setNome(dto.nome());
        motorista.setCnh(dto.cnh());
        motorista.setVeiculo(dto.veiculo());
        motorista.setCidadeBase(dto.cidadeBase());
        return motorista;
    }

    /**
     * Converte uma entidade Motorista para um DTO de resposta
     * @param motorista entidade persistida
     * @return da entidade Motorista
     */
    public MotoristaResponseDTO toResponse(Motorista motorista) {
        return new MotoristaResponseDTO(motorista.getId(), motorista.getNome(), motorista.getCnh(),
                motorista.getVeiculo(), motorista.getCidadeBase());
    }

    /**
     * Converte uma lista de Entidades para uma lista de DTOs de resposta
     * @param motoristas entidade persistida
     * @return representação pública de todos os motoristas retornados
     */
    public List<MotoristaResponseDTO> toResponseList(List<Motorista> motoristas) {
        return motoristas.stream().map(this::toResponse).toList();
    }
}
