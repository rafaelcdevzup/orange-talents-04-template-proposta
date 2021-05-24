package com.rafazup.bootcamp.cartao.associacartaoproposta;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.rafazup.bootcamp.proposta.Proposta;
import com.rafazup.bootcamp.proposta.PropostaRepository;
import com.rafazup.bootcamp.proposta.StatusProposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import feign.FeignException;

@Component
public class AssociaCartao {
    
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private AssociaCartaoClient associaCartaoClient;
    
    private final Logger logger = LoggerFactory.getLogger(AssociaCartao.class);

  @Transactional
  @Scheduled(fixedDelay = 10000)
  public void associarUmCartao() {
    logger.info("Verificação para associar cartão a proposta");
    List<Proposta> propostasParaSalvar =
        propostaRepository.findByCartaoIsNullAndStatusProposta(StatusProposta.ELEGIVEL).parallelStream()
            .peek(propostaAssocia -> {
                  try {
                    CartaoResponse cartao = associaCartaoClient.getCartao(propostaAssocia.getId());
                    propostaAssocia.setCartao(cartao.toModel(propostaAssocia));
                    logger.info("Proposta id={} proposta associada", propostaAssocia.getId());
                  } catch (FeignException e) {
                    logger.info("Proposta id={} proposta sem cartão", propostaAssocia.getId());
                  }
                }).collect(Collectors.filtering(proposta -> proposta.getCartao() != null, Collectors.toList()));
    propostaRepository.saveAll(propostasParaSalvar);
  }
}
