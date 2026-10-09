package com.api.logistica.service;

import com.api.logistica.dto.motorista.MotoristaRequestDTO;
import com.api.logistica.dto.motorista.MotoristaResponseDTO;
import com.api.logistica.entity.Motorista;
import com.api.logistica.exceptions.RegraDeNegocioException;
import com.api.logistica.mapper.MotoristaMapper;
import com.api.logistica.repository.MotoristaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service responsavel pelas regras de negocio * relacionadas ao gerenciamento de motoristas
 */
@Service
public class MotoristaService {

    private final MotoristaRepository repository;
    private final MotoristaMapper mapper;

    public MotoristaService(MotoristaRepository repository, MotoristaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Lança RegraDeNegocioException (400) se a CNH já estiver cadastrada.

    /**
     * Cadastra um novo motorista na base de dados após validar unicidade da cnh
     * @param dto Objeto contendo os dados de entrada para a criação do motorista
     * @return DTO {@link MotoristaResponseDTO} com os dados do motorista persistido
     * @throws RegraDeNegocioException Se já existir um motorista cadastrado com a mesma cnh
     */
    public MotoristaResponseDTO cadastrar(MotoristaRequestDTO dto) {
        if (repository.existePorCnh(dto.cnh())) {
            throw new RegraDeNegocioException("Já existe um motorista cadastrado com esta CNH.");
        }
        Motorista salvo = repository.salvar(mapper.toEntity(dto));
        return mapper.toResponse(salvo);
    }

    /**
     * Retorna todos os motoristas cadastrados
     * @return Lista de dto {@link MotoristaResponseDTO}
     * representando os motoristas já cadastrados
     */
    public List<MotoristaResponseDTO> listarTodos() {
        return mapper.toResponseList(repository.listarTodos());
    }
}
