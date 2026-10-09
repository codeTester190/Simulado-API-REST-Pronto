package com.api.logistica.mapper;

import com.api.logistica.dto.historico.HistoricoRequestDTO;
import com.api.logistica.dto.historico.HistoricoResponseDTO;
import com.api.logistica.entity.Historico;
import org.springframework.stereotype.Component;

@Component
public class HistoricoMapper {
    /**
     * Converte dados de criação para entidade Historico
     * @param dto request dados recebidos para criação
     * @return entidade Historico
     */
    public Historico toEntity(HistoricoRequestDTO dto) {
        Historico historico = new Historico();
        historico.setDescricao(dto.descricao());
        return historico;
    }

    /**
     * Converte uma entidade Historico para um DTO de resposta
     * @param historico entidade persistida
     * @return representação pública do historico
     */
    public HistoricoResponseDTO toResponse(Historico historico) {
        return new HistoricoResponseDTO(historico.getId(), historico.getEntregaId(),
                historico.getDescricao(), historico.getDataHora());
    }
}
