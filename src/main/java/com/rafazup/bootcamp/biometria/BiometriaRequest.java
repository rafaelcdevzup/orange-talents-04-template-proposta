package com.rafazup.bootcamp.biometria;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.rafazup.bootcamp.cartao.Cartao;

public class BiometriaRequest {

    @NotBlank @NotNull
    private String fingerprint;

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria toModel(Optional<Cartao> cartao){
        return new Biometria(Base64.getDecoder().decode(fingerprint.getBytes(StandardCharsets.UTF_8)), cartao.get());
    }
}