package com.api.logistica.entity;

import java.math.BigDecimal;
import com.api.logistica.entity.enums.StatusPedido;

/**
 * Classe de Pedido que representa uma entidade
 * <p>
 *     Essa entidade contem os dados internos utilizados pela camada de persistencia
 * </p>
 */

public class Pedido {

    private Long id;
    private Long clienteId;
    private BigDecimal volumeM3;
    private BigDecimal pesoKg;
    private StatusPedido status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public BigDecimal getVolumeM3() { return volumeM3; }
    public void setVolumeM3(BigDecimal volumeM3) { this.volumeM3 = volumeM3; }

    public BigDecimal getPesoKg() { return pesoKg; }
    public void setPesoKg(BigDecimal pesoKg) { this.pesoKg = pesoKg; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

}
