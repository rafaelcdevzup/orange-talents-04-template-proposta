package com.rafazup.bootcamp.cartao.bloqueiocartao;

import java.time.Instant;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rafazup.bootcamp.cartao.Cartao;
import com.rafazup.bootcamp.servicosutils.RequestUserIn;

@Entity
@Table(name = "tb_bloqueio")
public class Bloqueio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant bloqueio = Instant.now();
    private RequestUserIn requestIn;
    @ManyToOne
    private Cartao cartao;
    
    @Deprecated
    public Bloqueio(){

    }

    public Bloqueio(RequestUserIn requestIn, Cartao cartao) {
        this.requestIn = requestIn;
        this.cartao = cartao;
    }

    public Instant getBloqueio() {
        return bloqueio;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public RequestUserIn getRequestIn() {
        return requestIn;
    }
}
