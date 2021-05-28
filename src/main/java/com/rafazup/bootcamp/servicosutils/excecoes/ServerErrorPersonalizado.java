package com.rafazup.bootcamp.servicosutils.excecoes;

import org.springframework.http.HttpStatus;

public class ServerErrorPersonalizado extends RuntimeException {
    
    private HttpStatus httpStatus;
    private String motivo;
    
    
    public ServerErrorPersonalizado(HttpStatus httpStatus, String motivo) {
        this.httpStatus = httpStatus;
        this.motivo = motivo;
    }

    public ServerErrorPersonalizado(int status, String motivo) {
        this(HttpStatus.valueOf(status),motivo);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMotivo() {
        return motivo;
    }
}
