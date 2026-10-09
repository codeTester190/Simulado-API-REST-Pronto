package com.api.logistica.mapper;

import com.api.logistica.dto.entrega.EntregaRequestDTO;
import com.api.logistica.dto.entrega.EntregaResponseDTO;
import com.api.logistica.entity.Entrega;
import org.springframework.stereotype.Component;

@Component
public class EntregaMapper {
    /**
     * Converte dados de criação para entidade Entrega
     * @param dto request dados recebidos para criação
     * @return entidade entrega
     */
    public Entrega toEntity(EntregaRequestDTO dto) {
        Entrega entrega = new Entrega();
        entrega.setPedidoId(dto.pedidoId());
        entrega.setMotoristaId(dto.motoristaId());
        return entrega;
    }

    /**
     * Converte uma entidade Entrega para um DTO de resposta
     * @param entrega entidade persistida
     * @return representação pública do cliente
     */
    public EntregaResponseDTO toResponse(Entrega entrega) {
        return new EntregaResponseDTO(entrega.getId(), entrega.getPedidoId(),
                entrega.getMotoristaId(), entrega.getStatus());
    }
}
