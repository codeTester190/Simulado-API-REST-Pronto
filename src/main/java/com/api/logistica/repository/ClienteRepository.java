package com.api.logistica.repository;

import com.api.logistica.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepository {

    private final AtomicLong sequencialId = new AtomicLong(1);
    private final List<Cliente> clientes = new CopyOnWriteArrayList<>();

    public Cliente salvar(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(sequencialId.getAndIncrement());
            clientes.add(cliente);
        }
        return cliente;
    }

    public List<Cliente> listarTodos() {
        return List.copyOf(clientes);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Optional<Cliente> buscarPorDocumento(String documento) {
        return clientes.stream().filter(c -> c.getCpfCnpj().equals(documento)).findFirst();
    }

    public boolean existePorDocumento(String documento) {
        return clientes.stream().anyMatch(c -> c.getCpfCnpj().equals(documento));
    }

    public void deletar(Long id) {
        clientes.removeIf(c -> c.getId().equals(id));
    }
}
