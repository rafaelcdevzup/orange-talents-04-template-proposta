package com.rafazup.bootcamp.proposta.analisesolicitacao;

import com.rafazup.bootcamp.proposta.StatusProposta;

public enum ResultadoSolicitacao {
    COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusProposta.ELEGIVEL);

    private StatusProposta statusProposta;

    ResultadoSolicitacao(StatusProposta statusProposta){
        this.statusProposta = statusProposta;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
