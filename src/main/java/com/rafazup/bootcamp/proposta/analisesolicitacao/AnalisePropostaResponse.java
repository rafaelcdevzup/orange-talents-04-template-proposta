package com.rafazup.bootcamp.proposta.analisesolicitacao;

import com.rafazup.bootcamp.proposta.StatusProposta;

public class AnalisePropostaResponse {

    private String documento;
    private String nome;
    private Long idProposta;
    private ResultadoSolicitacao resultadoSolicitacao;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public StatusProposta statusProposta() {
        return resultadoSolicitacao.getStatusProposta();
    }
}
