package com.br.service.everson.libraryapi.api.pessoa.service;

import com.br.service.everson.libraryapi.LibraryApiApplicationTests;
import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;
import com.br.service.everson.libraryapi.core.modelmapper.MapperConvert;
import com.br.service.everson.libraryapi.domain.model.Documento;
import com.br.service.everson.libraryapi.domain.model.Pessoa;
import com.br.service.everson.libraryapi.domain.repository.DocumentoRepository;
import com.br.service.everson.libraryapi.domain.repository.PessoaRepository;
import com.br.service.everson.libraryapi.domain.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;


public class PessoaServiceTest extends LibraryApiApplicationTests {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    MapperConvert mapperConvert;

    private Pessoa pessoa;

    private Documento documento;

    @BeforeEach
    void executarAntesTest (){
        documento = criacaoDeDocumentoParaTeste();
        pessoa = criacaoDePessoaParaTeste(documento);

        pessoa.setDocumento(documento);
        pessoaService.create(mapperConvert.mapEntityToDto(pessoa, PessoaInputDto.class));
    }
    @Test
    void deveRetornarListaDePessoasPorNome() {

        Page<PessoaOutputDto> pessoas = pessoaService.findAll(0,1,"id","asc",pessoa.getNome());
        assertFalse(pessoas.isEmpty());
        assertEquals(pessoa.getNome(), pessoas.getContent().get(0).getNome());
    }

    @Test
    void deveRetornaListaVazia(){
        Page<PessoaOutputDto> pessoas = pessoaService.findAll(0,1,"id","asc","nome errado");
        assertTrue(pessoas.isEmpty());

    }

}
