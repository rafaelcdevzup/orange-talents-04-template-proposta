package com.rafazup.bootcamp.proposta.analisesolicitacao;

import com.rafazup.bootcamp.proposta.Proposta;

public class AnalisePropostaRequest {

    private String documento;
    private String nome;
    private Long idProposta;

    @Deprecated
    public AnalisePropostaRequest() {

    }

    public AnalisePropostaRequest(Proposta entidade) {
        documento = entidade.getDocumento();
        nome = entidade.getNome();
        idProposta = entidade.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
