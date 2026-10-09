package com.api.logistica.entity;

/**
 * Classe de Motorista que representa uma entidade.
 *
 * <p>
 *     Essa entidade contém os dados internos utilizados pela camada de persistencia
 * </p>
 */
public class Motorista {

    private Long id;
    private String nome;
    private String cnh;
    private String veiculo;
    private String cidadeBase;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public String getVeiculo() { return veiculo; }
    public void setVeiculo(String veiculo) { this.veiculo = veiculo; }

    public String getCidadeBase() { return cidadeBase; }
    public void setCidadeBase(String cidadeBase) { this.cidadeBase = cidadeBase; }

}
