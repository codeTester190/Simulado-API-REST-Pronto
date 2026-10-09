package com.api.logistica.service;

import com.api.logistica.dto.pedido.PedidoRequestDTO;
import com.api.logistica.dto.pedido.PedidoResponseDTO;
import com.api.logistica.entity.Pedido;
import com.api.logistica.entity.enums.StatusPedido;
import com.api.logistica.exceptions.RecursoNaoEncontradoException;
import com.api.logistica.exceptions.RegraDeNegocioException;
import com.api.logistica.mapper.PedidoMapper;
import com.api.logistica.repository.ClienteRepository;
import com.api.logistica.repository.EntregaRepository;
import com.api.logistica.repository.PedidoRepository;
import org.springframework.stereotype.Service;

/**
 * A service responsavel pelas regras de négocio * relacionadas ao gerenciamento de pedido
 */
@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final EntregaRepository entregaRepository;
    private final PedidoMapper mapper;

    public PedidoService(PedidoRepository repository, ClienteRepository clienteRepository,
                         EntregaRepository entregaRepository, PedidoMapper mapper) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.entregaRepository = entregaRepository;
        this.mapper = mapper;
    }

    /**
     * Cadastra um novo pedido na base de dados após validar a existencia do cliente
     * @param dto Objeto contendo os dados de entrada para a criação do pedido
     * @return DTO {@link PedidoResponseDTO} com os dados do pedido persistido
     * @throws RecursoNaoEncontradoException se o cliente não for encontrado com o ID fornecido
     */
    public PedidoResponseDTO cadastrar(PedidoRequestDTO dto) {
        clienteRepository.buscarPorId(dto.clienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Cliente não encontrado com o id: " + dto.clienteId()));
        Pedido pedido = mapper.toEntity(dto);
        pedido.setStatus(StatusPedido.PENDENTE);
        return mapper.toResponse(repository.salvar(pedido));
    }

    //404 se o pedido não existe; 400 (RegraDeNegocioException) se já estiver cancelado ou se já possuir entrega.

    /**
     * Cancelar o pedido com base no Identificador unico fornecido pelo cliente após validar a existencia do pedido
     * @param id Identificador Unico fornecido pelo usuario para cancelar o pedido
     * @return DTO {@link PedidoResponseDTO} com os dados do pedido persistido
     * @throws RecursoNaoEncontradoException se o Pedido não for encontrado pelo Id informado
     * @throws RegraDeNegocioException verifica se o usuario quer cancelar um pedido já cancelado ou cancelar um
     * pedido que já tem uma entrega associado
     */
    public PedidoResponseDTO cancelar(Long id) {
        Pedido pedido = repository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com o id: " + id));
        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new RegraDeNegocioException("O pedido já está cancelado.");
        }
        if (entregaRepository.existePorPedidoId(id)) {
            throw new RegraDeNegocioException("O pedido não pode ser cancelado pois já possui entrega.");
        }
        pedido.setStatus(StatusPedido.CANCELADO);
        return mapper.toResponse(pedido);
    }
}
