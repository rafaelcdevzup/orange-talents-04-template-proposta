package com.rafazup.bootcamp.cartao.associacartaoproposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "associa-cartao", url = "http://localhost:8888")
public interface AssociaCartaoClient {
    
    @GetMapping("/cartoes?idProposta={idProposta}")
    CartaoResponse getCartao(@PathVariable("idProposta")Long idProposta);
    
}
