package com.rafazup.bootcamp.proposta;

import java.math.BigDecimal;

import com.rafazup.bootcamp.cartao.associacartaoproposta.CartaoResponse;

public class PropostaResponse {
    
    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endreco;
    private BigDecimal salario;
    private StatusProposta proposta;
    private CartaoResponse cartao;

    public PropostaResponse(Proposta entidade) {
        id = entidade.getId();
        documento = entidade.getDocumento();
        email = entidade.getEmail();
        nome = entidade.getNome();
        endreco = entidade.getEndereco();
        salario = entidade.getSalario();
        proposta = entidade.getStatusProposta();
        cartao = entidade.getCartao() != null ? new CartaoResponse(entidade.getCartao()) : null; 
        
    }

    public Long getId() {
        return id;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getEndreco() {
        return endreco;
    }
    
    public BigDecimal getSalario() {
        return salario;
    }
    
    public StatusProposta getProposta() {
        return proposta;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }
}
