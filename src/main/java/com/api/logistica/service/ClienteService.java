package com.api.logistica.service;

import com.api.logistica.dto.cliente.ClienteRequestDTO;
import com.api.logistica.dto.cliente.ClienteResponseDTO;
import com.api.logistica.entity.Cliente;
import com.api.logistica.exceptions.RecursoNaoEncontradoException;
import com.api.logistica.exceptions.RegraDeNegocioException;
import com.api.logistica.mapper.ClienteMapper;
import com.api.logistica.repository.ClienteRepository;
import com.api.logistica.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service responsavel pelas regras de négocio * relacionadas ao gerenciamento de cliente
 */
@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, PedidoRepository pedidoRepository, ClienteMapper mapper) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.mapper = mapper;
    }

    /**
     * Retorna todos clientes cadastrados
     * @return Lista de dto {@link ClienteResponseDTO}
     * representando os clientes já encontrados
     */
    public List<ClienteResponseDTO> listarTodos() {
        return mapper.toResponseList(repository.listarTodos());
    }


    /**
     * Busca um cliente pelo seu Identificador unico
     * @param id Identificador Unico do cliente a ser localizado
     * @return {@link ClienteResponseDTO} representando o cliente encontrado
     * @throws RecursoNaoEncontradoException (404) se o cliente não existir.
     */
    public ClienteResponseDTO buscarPorId(Long id) {
        return mapper.toResponse(buscarOuFalhar(id));
    }

    // Lança RecursoNaoEncontradoException (404) se não houver cliente com o documento.

    /**
     * Busca um cliente pelo cpf ou cnpj
     * @param documento Cpf ou CNPJ do cliente a ser localizado
     * @return {@link ClienteResponseDTO} representando o cliente encontrado
     * @throws RecursoNaoEncontradoException (404) se o cliente não existir
     */
    public ClienteResponseDTO buscarPorDocumento(String documento) {
        return repository.buscarPorDocumento(documento)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Cliente não encontrado com o documento: " + documento));
    }

    /**
     * Cadastra um novo cliente na base de dados após validar a unicidade do cpf ou cnpj
     * @param dto Objeto contento os dados de entrada para criação do cliente
     * @return DTO {@link ClienteResponseDTO} com os dados do cliente persistido
     * @throws RegraDeNegocioException Se já existir um cliente cadastrado com o mesmo cpf ou cnpj
     */
    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        if (repository.existePorDocumento(dto.cpfCnpj())) {
            throw new RegraDeNegocioException("Já existe um cliente cadastrado com este CPF/CNPJ.");
        }
        Cliente salvo = repository.salvar(mapper.toEntity(dto));
        return mapper.toResponse(salvo);
    }

    /**
     * Remove um cliente da base de dados pelo seu Identificador unico
     * @param id Identificador uncico do cliente a ser removido
     * @throws RegraDeNegocioException 404 se o cliente não existe; 400 (RegraDeNegocioException) se possuir pedidos vinculados.
     */
    public void deletar(Long id) {
        buscarOuFalhar(id);
        if (pedidoRepository.existePorClienteId(id)) {
            throw new RegraDeNegocioException("O cliente não pode ser removido pois possui pedidos vinculados.");
        }
        repository.deletar(id);
    }

    /**
     * Busca o cliente pelo o ID informado
     * @param id {@link Cliente} a ser encontrado
     * @return entidade cliente
     * @throws RecursoNaoEncontradoException se nenhum cliente for encontrado
     */
    private Cliente buscarOuFalhar(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o id: " + id));
    }
}
