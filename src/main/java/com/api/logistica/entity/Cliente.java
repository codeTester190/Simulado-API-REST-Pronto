package com.api.logistica.entity;

/**
 * A classe Cliente representa uma entidade
 *
 * <p>
 *     Essa entidade contem os dados internos utilizados pela camada de persistencia
 * </p>
 */

public class Cliente {

    private Long id;
    private String nome;
    private String cpfCnpj;
    private String endereco;
    private String cidade;
    private String estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

}
