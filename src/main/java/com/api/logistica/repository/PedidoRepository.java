package com.api.logistica.repository;

import com.api.logistica.entity.Pedido;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;

@Repository
public class PedidoRepository {

    private final AtomicLong sequencialId = new AtomicLong(1);
    private final List<Pedido> pedidos = new CopyOnWriteArrayList<>();

    public Pedido salvar(Pedido pedido) {
        if (pedido.getId() == null) {
            pedido.setId(sequencialId.getAndIncrement());
            pedidos.add(pedido);
        }
        return pedido;
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public boolean existePorClienteId(Long clienteId) {
        return pedidos.stream().anyMatch(p -> p.getClienteId().equals(clienteId));
    }
}
