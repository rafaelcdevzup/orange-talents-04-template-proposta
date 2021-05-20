package com.rafazup.bootcamp.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.rafazup.bootcamp.proposta.analisesolicitacao.AnalisePropostaRequest;
import com.rafazup.bootcamp.proposta.analisesolicitacao.AnalisePropostaResponse;
import com.rafazup.bootcamp.proposta.analisesolicitacao.AnaliseSolicitacaoClient;
import com.rafazup.bootcamp.servicosutils.excecoes.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private AnaliseSolicitacaoClient analiseSolicitacaoClient;

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @PostMapping
    public ResponseEntity<PropostaRequest> insert(@RequestBody @Valid PropostaRequest request) {
        Proposta proposta = request.toModel();
        if (propostaRepository.existsByDocumento(request.getDocumento())) {
            return ResponseEntity.unprocessableEntity().build();
        }
        propostaRepository.save(proposta);

        AnalisePropostaRequest analiseRequest = new AnalisePropostaRequest(proposta);

        try {
            AnalisePropostaResponse resultadoConsulta = analiseSolicitacaoClient.consulta(analiseRequest);
            StatusProposta status = resultadoConsulta.statusProposta();

            proposta.setStatusProposta(status);

        } catch (FeignException.UnprocessableEntity unprocessableEntity) {
            proposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
        }
        propostaRepository.save(proposta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaRequest> buscaProposta(@PathVariable Long id) {
        Optional<Proposta> proposta = propostaRepository.findById(id);
        proposta.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado " + id));
        return ResponseEntity.ok().body(new PropostaRequest(proposta.get()));
    }
}