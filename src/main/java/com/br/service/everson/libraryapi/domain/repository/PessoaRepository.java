package com.br.service.everson.libraryapi.domain.repository;

import com.br.service.everson.libraryapi.domain.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    @Query(value = "Select p from Pessoa p where(:nome is null or lower(p.nome) like lower(concat('%', :nome, '%'))) " )
    Page<Pessoa> findWithFilter(@Param("nome")String nome, Pageable page);

    @Query(value = "Select p from Pessoa p where p.documento.cpf = :cpf")
    Optional<Pessoa> buscarPeloCPF(@Param("cpf") String cpf);

}
