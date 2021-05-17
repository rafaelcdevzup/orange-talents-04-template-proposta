package com.rafazup.bootcamp.proposta.analisesolicitacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analise-proposta", url="http://localhost:9999")
public interface AnaliseSolicitacaoClient {
    
    @PostMapping("/api/solicitacao")
    AnalisePropostaResponse consulta (AnalisePropostaRequest request);
}
