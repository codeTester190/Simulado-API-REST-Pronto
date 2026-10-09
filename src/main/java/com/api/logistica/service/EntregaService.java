package com.api.logistica.service;

import com.api.logistica.dto.entrega.EntregaRequestDTO;
import com.api.logistica.dto.entrega.EntregaResponseDTO;
import com.api.logistica.dto.entrega.EntregaStatusDTO;
import com.api.logistica.dto.historico.HistoricoRequestDTO;
import com.api.logistica.dto.historico.HistoricoResponseDTO;
import com.api.logistica.entity.Entrega;
import com.api.logistica.entity.Historico;
import com.api.logistica.entity.Pedido;
import com.api.logistica.entity.enums.StatusEntrega;
import com.api.logistica.entity.enums.StatusPedido;
import com.api.logistica.exceptions.RecursoNaoEncontradoException;
import com.api.logistica.exceptions.RegraDeNegocioException;
import com.api.logistica.mapper.EntregaMapper;
import com.api.logistica.mapper.HistoricoMapper;
import com.api.logistica.repository.EntregaRepository;
import com.api.logistica.repository.HistoricoRepository;
import com.api.logistica.repository.MotoristaRepository;
import com.api.logistica.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service responsavel pelas regras de négocio * relacionadas ao gerenciamento de Entrega
 */
@Service
public class EntregaService {

    private final EntregaRepository repository;
    private final PedidoRepository pedidoRepository;
    private final MotoristaRepository motoristaRepository;
    private final HistoricoRepository historicoRepository;
    private final EntregaMapper mapper;
    private final HistoricoMapper historicoMapper;

    public EntregaService(EntregaRepository repository, PedidoRepository pedidoRepository,
                          MotoristaRepository motoristaRepository, HistoricoRepository historicoRepository,
                          EntregaMapper mapper, HistoricoMapper historicoMapper) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.motoristaRepository = motoristaRepository;
        this.historicoRepository = historicoRepository;
        this.mapper = mapper;
        this.historicoMapper = historicoMapper;
    }

    /**
     * Gera a entrega com status EM_ROTA na base de dados após validar a unicidade pedido (se tem um cadastrado ou não) e motorista validar se existir um motorista para atribuilo a entrega.
     * 404 se o pedido ou o motorista não existirem;
     * @param dto objeto contendo os dados de entrada para a criação de entrega
     * @return DTO {@link EntregaResponseDTO} com os dados entrega persistido
     * @throws RegraDeNegocioException gera erro 400 se o pedido estiver cancelado ou já possuir entrega.
     * @throws RecursoNaoEncontradoException gera erro 400 se o pedido não existir ou motorista não existir
     */
    public EntregaResponseDTO cadastrar(EntregaRequestDTO dto) {
        Pedido pedido = pedidoRepository.buscarPorId(dto.pedidoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pedido não encontrado com o id: " + dto.pedidoId()));
        motoristaRepository.buscarPorId(dto.motoristaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Motorista não encontrado com o id: " + dto.motoristaId()));

        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new RegraDeNegocioException("Não é possível gerar entrega para um pedido cancelado.");
        }
        if (repository.existePorPedidoId(dto.pedidoId())) {
            throw new RegraDeNegocioException("Este pedido já possui uma entrega.");
        }

        Entrega entrega = mapper.toEntity(dto);
        entrega.setStatus(StatusEntrega.EM_ROTA);
        return mapper.toResponse(repository.salvar(entrega));
    }

    /**
     * Atualizar status de uma Entrega para o status ENTREGUE ou ATRASADA.
     * @param id Identificador Unico de entrega a ser atualizada
     * @param dto DTO contendo o novo status desejado
     * @return DTO {@link EntregaResponseDTO} contendo os dados atualizados da entrega
     * @throws RegraDeNegocioException se o status inserido for diferente de (ATRASADA ou ENTREGUE)
     * @throws RecursoNaoEncontradoException se a entrega não for encontrado pelo ID informado
     */
    public EntregaResponseDTO atualizarStatus(Long id, EntregaStatusDTO dto) {
        Entrega entrega = buscarOuFalhar(id);
        StatusEntrega novo = dto.status();
        StatusEntrega atual = entrega.getStatus();

        if (novo == StatusEntrega.EM_ROTA) {
            throw new RegraDeNegocioException("O status deve ser ENTREGUE ou ATRASADA.");
        }
        boolean permitido = atual == StatusEntrega.EM_ROTA
                || (atual == StatusEntrega.ATRASADA && novo == StatusEntrega.ENTREGUE);
        if (!permitido) {
            throw new RegraDeNegocioException("Transição de status inválida: " + atual + " -> " + novo);
        }

        entrega.setStatus(novo);
        return mapper.toResponse(entrega);
    }

    /**
     * Adicionar ao historico uma entrega realizada
     * @param entregaId identificador unico da entrega
     * @param dto Objeto contendo os dados de entrada para a criação do historico
     * @return dto DTO {@link HistoricoResponseDTO} com os dados persistidos
     */
    public HistoricoResponseDTO adicionarHistorico(Long entregaId, HistoricoRequestDTO dto) {
        buscarOuFalhar(entregaId);
        Historico historico = historicoMapper.toEntity(dto);
        historico.setEntregaId(entregaId);
        historico.setDataHora(LocalDateTime.now());
        return historicoMapper.toResponse(historicoRepository.salvar(historico));
    }

    /**
     * Busca entrega pelo ID informado
     * @param id {@link Entrega} a ser encontrado
     * @return Entidade entrega
     * @throws RecursoNaoEncontradoException se nenhuma entrega for encontrada
     */
    private Entrega buscarOuFalhar(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Entrega não encontrada com o id: " + id));
    }
}
