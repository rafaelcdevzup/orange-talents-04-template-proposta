package com.rafazup.bootcamp.cartao.associacartaoproposta;

import com.rafazup.bootcamp.cartao.bloqueiocartao.BloqueioRequest;
import com.rafazup.bootcamp.cartao.bloqueiocartao.BloqueioResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "associa-cartao", url = "http://localhost:8888")
public interface AssociaCartaoClient {
    
    @GetMapping("/cartoes?idProposta={idProposta}")
    CartaoResponse getCartao(@PathVariable("idProposta")Long idProposta);

      @PostMapping("/cartoes/{id}/bloqueios")
      BloqueioResponse bloqueioCartao( @PathVariable("id") Long id, @RequestBody BloqueioRequest body);
    //Parei aqui, implementar as classes criadas.
}
