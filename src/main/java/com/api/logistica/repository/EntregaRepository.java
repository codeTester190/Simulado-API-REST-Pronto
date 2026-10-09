package com.api.logistica.repository;

import com.api.logistica.entity.Entrega;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EntregaRepository {

    private final AtomicLong sequencialId = new AtomicLong(1);
    private final List<Entrega> entregas = new CopyOnWriteArrayList<>();

    public Entrega salvar(Entrega entrega) {
        if (entrega.getId() == null) {
            entrega.setId(sequencialId.getAndIncrement());
            entregas.add(entrega);
        }
        return entrega;
    }

    public List<Entrega> listarTodos() {
        return List.copyOf(entregas);
    }

    public Optional<Entrega> buscarPorId(Long id) {
        return entregas.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public boolean existePorPedidoId(Long pedidoId) {
        return entregas.stream().anyMatch(e -> e.getPedidoId().equals(pedidoId));
    }
}
