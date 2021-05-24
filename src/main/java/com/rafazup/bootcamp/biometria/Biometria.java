package com.rafazup.bootcamp.biometria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rafazup.bootcamp.cartao.Cartao;

@Entity
@Table(name = "tb_biometria")
public class Biometria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] fingerprint;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria(){

    }

    public Biometria(byte[] fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public Cartao getCartao() {
        return cartao;
    }
}