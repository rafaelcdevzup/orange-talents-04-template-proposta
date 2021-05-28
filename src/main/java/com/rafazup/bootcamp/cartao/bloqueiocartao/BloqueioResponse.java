package com.rafazup.bootcamp.cartao.bloqueiocartao;

public class BloqueioResponse {
    
    private StatusBloqueio status;

    public BloqueioResponse(StatusBloqueio status) {
        this.status = status;
    }

    public StatusBloqueio getStatus() {
        return status;
    }
}
