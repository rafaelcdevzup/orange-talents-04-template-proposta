package com.rafazup.bootcamp.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.rafazup.bootcamp.servicosutils.CPForCNPJ;

public class PropostaRequest {
    
    @NotBlank @CPForCNPJ
    private String documento;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String endereco;
    @NotBlank
    private String nome;
    @NotNull @Positive
    private BigDecimal salario;
    private StatusProposta statusProposta;
    
    /*@Deprecated
    public PropostaRequest(){
    
    }*/
    
    public PropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String endereco,
            @NotBlank String nome, @NotNull @Positive BigDecimal salario, StatusProposta statusProposta) {
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.nome = nome;
        this.salario = salario;
        this.statusProposta = statusProposta;
    }

    public PropostaRequest(Proposta entidade) {
        this.documento = entidade.getDocumento();
        this.email = entidade.getEmail();
        this.endereco = entidade.getEndereco();
        this.nome = entidade.getNome();
        this.salario = entidade.getSalario();
        this.statusProposta = entidade.getStatusProposta();
    }
    
    public Proposta toModel() {
        return new Proposta(this.documento,this.email,this.nome,this.endereco,this.salario, this.statusProposta);
    }

    public String getEndereco(){
        return endereco;
    }

    public String getDocumento() {
        return documento;
    }
}