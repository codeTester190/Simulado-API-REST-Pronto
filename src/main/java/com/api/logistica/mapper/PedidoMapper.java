package com.api.logistica.mapper;

import com.api.logistica.dto.pedido.PedidoRequestDTO;
import com.api.logistica.dto.pedido.PedidoResponseDTO;
import com.api.logistica.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    /**
     * Converte dados de criação para uma entidade Pedido
     * @param dto request dados recebidos para criação
     * @return entidade Pedido
     */
    public Pedido toEntity(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(dto.clienteId());
        pedido.setVolumeM3(dto.volumeM3());
        pedido.setPesoKg(dto.pesoKg());
        return pedido;
    }

    /**
     * Converte uma entidade pedido para um dto de resposta
     * @param pedido entidade persistida
     * @return representação publica de pedido
     */
    public PedidoResponseDTO toResponse(Pedido pedido) {
        return new PedidoResponseDTO(pedido.getId(), pedido.getClienteId(), pedido.getVolumeM3(),
                pedido.getPesoKg(), pedido.getStatus());
    }
}
