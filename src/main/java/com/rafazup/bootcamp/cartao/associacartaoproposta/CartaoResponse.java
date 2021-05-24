package com.rafazup.bootcamp.cartao.associacartaoproposta;

import java.time.Instant;

import com.rafazup.bootcamp.cartao.Cartao;
import com.rafazup.bootcamp.proposta.Proposta;

public class CartaoResponse {

    private Long id;
    private Instant emissao;
    private String titular;
    private Long idProposta;
    
    
    public CartaoResponse(Long id, Instant emissao, String titular, Long idProposta) {
        this.id = id;
        this.emissao = emissao;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    public CartaoResponse(Cartao entidade) {
        id = entidade.getId();
        emissao = entidade.getEmissao();
        titular = entidade.getTitular();
        idProposta = entidade.getProposta().getId();
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

    public Long getIdProposta() {
        return idProposta;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(id, emissao, titular, proposta);
    }    
}
