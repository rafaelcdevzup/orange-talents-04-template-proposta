package com.rafazup.bootcamp.biometria;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.rafazup.bootcamp.cartao.Cartao;
import com.rafazup.bootcamp.cartao.CartaoRepository;
import com.rafazup.bootcamp.servicosutils.excecoes.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/biometrias")
public class BiometriaController {
    
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BiometriaRepository biometriaRepository;

    
    @PostMapping("/{id}")
    public ResponseEntity<?> criandoBiometria(@PathVariable Long id, @RequestBody @Valid BiometriaRequest request){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        cartao.orElseThrow(()-> new ResourceNotFoundException("ID não encontrado " + id));
        Biometria biometria = biometriaRepository.save(request.toModel(cartao));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
