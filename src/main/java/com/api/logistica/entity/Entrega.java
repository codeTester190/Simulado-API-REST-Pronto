package com.api.logistica.entity;

import com.api.logistica.entity.enums.StatusEntrega;

/**
 * Classe Entrega que representa uma entidade
 *
 * <p>
 *     Essa entidade contem os dados internos utilizados pela camada de persistencia
 * </p>
 */
public class Entrega {

    private Long id;
    private Long pedidoId;
    private Long motoristaId;
    private StatusEntrega status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public Long getMotoristaId() { return motoristaId; }
    public void setMotoristaId(Long motoristaId) { this.motoristaId = motoristaId; }

    public StatusEntrega getStatus() { return status; }
    public void setStatus(StatusEntrega status) { this.status = status; }

}
