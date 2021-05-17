package com.rafazup.bootcamp.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    //Optional<Proposta> findByDocumento(String documento);
    boolean existsByDocumento(String documento);
}
