package com.br.service.everson.libraryapi.api.pessoa;

import com.br.service.everson.libraryapi.LibraryApiApplicationTests;
import com.br.service.everson.libraryapi.domain.model.Documento;
import com.br.service.everson.libraryapi.domain.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class PessoaServiceTest extends LibraryApiApplicationTests {

//    @Autowired
//    private PessoaRepository pessoaRepository;

    private Documento documento;

    private Pessoa pessoa;

//    @BeforeEach
//    void executarAntesTest (){
//        documento = criacaoDeDocumentoParaTeste();
//        pessoa = criacaoDePessoaParaTeste(documento);
//
//        documentoRepository.save(documento);
//        pessoaRepository.save(pessoa);
//    }
//    @Test
//    void deveRetornarListaDePessoasPorNome() {
//
//        Page<PessoaOutputDTO> pessoas = pessoaService.buscaComFiltros(0,1,"id","asc",pessoa.getNome(),null,null,null,null);
//
//        assertFalse(pessoas.isEmpty());
//        assertEquals(pessoa.getNome(), pessoas.getContent().get(0).getNome());
//    }

}
