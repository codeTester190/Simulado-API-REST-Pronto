package com.api.logistica.entity;

import java.time.LocalDateTime;

/**
 * Classe Historico que representa uma entidade.
 * <p>
 *     Esssa entidade contém os dados internos utilizados para camada de persistencia
 * </p>
 */
public class Historico {

    private Long id;
    private Long entregaId;
    private String descricao;
    private LocalDateTime dataHora;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEntregaId() { return entregaId; }
    public void setEntregaId(Long entregaId) { this.entregaId = entregaId; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

}
