package com.api.logistica.repository;

import com.api.logistica.entity.Historico;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HistoricoRepository {

    private final AtomicLong sequencialId = new AtomicLong(1);
    private final List<Historico> historicos = new CopyOnWriteArrayList<>();

    public Historico salvar(Historico historico) {
        if (historico.getId() == null) {
            historico.setId(sequencialId.getAndIncrement());
            historicos.add(historico);
        }
        return historico;
    }

    public List<Historico> listarPorEntrega(Long entregaId) {
        return historicos.stream().filter(h -> h.getEntregaId().equals(entregaId)).toList();
    }
}
