package com.rafazup.bootcamp.proposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    //Optional<Proposta> findByDocumento(String documento);
    boolean existsByDocumento(String documento);
    //tentativa sem declaração de query
    List<Proposta> findByCartaoIsNullAndStatusProposta(@NonNull StatusProposta statusProposta);
}
