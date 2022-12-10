package com.br.service.everson.libraryapi.api.pessoa.controller;

import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;
import com.br.service.everson.libraryapi.api.pessoa.stub.PessoaStub;
import com.br.service.everson.libraryapi.domain.model.Pessoa;
import com.br.service.everson.libraryapi.domain.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class PessoaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PessoaService pessoaService;

    @MockBean
    ModelMapper modelMapper;

    private static ObjectMapper objectMapper = new ObjectMapper();

//    @Test
//    void deveCadastrarPessoaComStatus201(){
//        PessoaInputDto pessoa = modelMapper.map(PessoaStub.getPessoa(),PessoaInputDto.class);
//
//        Mockito.when(pessoaService.create(Mockito.any(PessoaInputDto.class))).thenReturn(pessoa);
//    }

//    @Test
//    void deveRetornarListaVaziaStatus204() throws Exception {
//        PageRequest paginacao = PageRequest.of(0, 10);
//        List<Pessoa> pessoas = new ArrayList<>();
//        Page<PessoaOutputDto> pessoasPage = new PageImpl<>(pessoas, paginacao, pessoas.size());
//        Mockito.when(pessoaService.buscaComFiltros(0, 10, "id", "asc", null,null,null,null,null))
//                .thenReturn(pessoasPage);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/pesquisar"))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//
//    }

}
