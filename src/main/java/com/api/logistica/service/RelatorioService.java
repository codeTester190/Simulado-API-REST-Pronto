package com.api.logistica.service;

import com.api.logistica.dto.relatorio.EntregasPorMotoristaDTO;
import com.api.logistica.entity.Entrega;
import com.api.logistica.repository.EntregaRepository;
import com.api.logistica.repository.MotoristaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service responsavel pelas regras de négocio * relacionametos ao gerenciamento de relatorio
 */
@Service
public class RelatorioService {

    private final EntregaRepository entregaRepository;
    private final MotoristaRepository motoristaRepository;

    public RelatorioService(EntregaRepository entregaRepository, MotoristaRepository motoristaRepository) {
        this.entregaRepository = entregaRepository;
        this.motoristaRepository = motoristaRepository;
    }

    /**
     * Retorna o total de entregas associadas a cada motorista cadastrado
     * @return Lista {@link EntregasPorMotoristaDTO} contendo ID, nome do motorista e a contagem de entregas
     */
    public List<EntregasPorMotoristaDTO> entregasPorMotorista() {
        Map<Long, Long> totais = entregaRepository.listarTodos().stream()
                .collect(Collectors.groupingBy(Entrega::getMotoristaId, Collectors.counting()));

        return motoristaRepository.listarTodos().stream()
                .map(m -> new EntregasPorMotoristaDTO(m.getId(), m.getNome(), totais.getOrDefault(m.getId(), 0L)))
                .toList();
    }
}
