package com.api.logistica.repository;

import com.api.logistica.entity.Motorista;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MotoristaRepository {

    private final AtomicLong sequencialId = new AtomicLong(1);
    private final List<Motorista> motoristas = new CopyOnWriteArrayList<>();

    public Motorista salvar(Motorista motorista) {
        if (motorista.getId() == null) {
            motorista.setId(sequencialId.getAndIncrement());
            motoristas.add(motorista);
        }
        return motorista;
    }

    public List<Motorista> listarTodos() {
        return List.copyOf(motoristas);
    }

    public Optional<Motorista> buscarPorId(Long id) {
        return motoristas.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public boolean existePorCnh(String cnh) {
        return motoristas.stream().anyMatch(m -> m.getCnh().equals(cnh));
    }
}
