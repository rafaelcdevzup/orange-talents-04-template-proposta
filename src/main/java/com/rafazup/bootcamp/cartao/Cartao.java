package com.rafazup.bootcamp.cartao;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.rafazup.bootcamp.proposta.Proposta;

@Entity
@Table(name = "tb_cartao")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant emissao;
    private String titular;
    @OneToOne(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Proposta proposta;
    @Enumerated(EnumType.STRING)
    private StatusCartao status = StatusCartao.ATIVO;

    @Deprecated
    public Cartao(){

    }

    public Cartao(Long id, Instant emissao, String titular, Proposta proposta) {
        this.emissao = emissao;
        this.titular = titular;
        this.proposta = proposta;
    }

    public Boolean bloquear() {
        if (status == StatusCartao.BLOQUEADO) 
        return false;
        status = StatusCartao.BLOQUEADO;
        return true;
    }

    public Long getId() {
        return id;
    }

    public Instant getEmissao() {
        return emissao;
    }

    public String getTitular() {
        return titular;
    }

    public Proposta getProposta() {
        return proposta;
    }    
}
