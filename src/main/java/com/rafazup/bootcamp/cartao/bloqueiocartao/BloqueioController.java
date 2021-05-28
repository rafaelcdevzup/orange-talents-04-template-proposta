package com.rafazup.bootcamp.cartao.bloqueiocartao;
import javax.servlet.http.HttpServletRequest;

import com.rafazup.bootcamp.cartao.Cartao;
import com.rafazup.bootcamp.cartao.CartaoRepository;
import com.rafazup.bootcamp.cartao.associacartaoproposta.AssociaCartaoClient;
import com.rafazup.bootcamp.servicosutils.RequestUserIn;
import com.rafazup.bootcamp.servicosutils.excecoes.ResourceNotFoundException;
import com.rafazup.bootcamp.servicosutils.excecoes.ServerErrorPersonalizado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.FeignException;

@RestController
public class BloqueioController {
    
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BloqueioRepository bloqueioRepository; 
    @Autowired
    private AssociaCartaoClient associaCartaoClient;
    

    @PostMapping("/cartoes/{id}/bloqueio")
    public ResponseEntity<Void> bloqueioCartao(@PathVariable Long id, HttpServletRequest request){

        Cartao cartao = cartaoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
        boolean cartaoBloqueado;

        try {
           BloqueioResponse response = associaCartaoClient.bloqueioCartao(cartao.getId(), new BloqueioRequest());
           cartaoBloqueado = response.getStatus() == StatusBloqueio.BLOQUEADO;        
        } catch (FeignException e) {
            cartaoBloqueado = false;
     
        } catch (Exception e){
            throw new ServerErrorPersonalizado(HttpStatus.INTERNAL_SERVER_ERROR, "Requisição falhou");
        }
        if(cartaoBloqueado && !cartao.bloquear()){
            throw new ServerErrorPersonalizado(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já foi bloqueado");
        }
        bloqueioRepository.save(new Bloqueio(new RequestUserIn(request), cartao));
        return ResponseEntity.ok().build();
    } 
}