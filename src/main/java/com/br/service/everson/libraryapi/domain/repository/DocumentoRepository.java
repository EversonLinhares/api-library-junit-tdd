package com.br.service.everson.libraryapi.domain.repository;

import com.br.service.everson.libraryapi.domain.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento,Long> {

    @Query(value = "Select * from documento d where d.cpf = :cpf", nativeQuery = true)
    Optional<Documento> buscarPorCPF(@Param("cpf") String cpf);

}
