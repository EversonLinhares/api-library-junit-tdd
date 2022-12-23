package com.br.service.everson.libraryapi.api.pessoa.controller;

import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;
import com.br.service.everson.libraryapi.api.pessoa.stub.PessoaStub;
import com.br.service.everson.libraryapi.domain.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PessoaControllerTest {

    static String URL_PESSOA = "/pessoa";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PessoaService pessoaService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Deve cadastrar uma pessoa e retornar PessoaOutputDto com status 201 create")
    void deveCadastrarPessoaRertornarDtoComStatus201() throws Exception {
        PessoaOutputDto pessoa = PessoaStub.getPessoaOutputDto();
        Mockito.when(pessoaService.create(Mockito.any(PessoaInputDto.class))).thenReturn(PessoaStub.getPessoaOutputDto());
        String json = objectMapper.writeValueAsString(pessoa);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URL_PESSOA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("nome").value(pessoa.getNome()));

        Mockito.verify(pessoaService, Mockito.times(1)).create(any());

    }

    @Test
    @DisplayName("deve retornar uma page vazia com status 204 no content")
    void deveRetornarListaVaziaStatus204() throws Exception {
        PageRequest paginacao = PageRequest.of(0, 10);
        List<PessoaOutputDto> pessoas = new ArrayList<>();
        Page<PessoaOutputDto> pessoasPage = new PageImpl(pessoas, paginacao, pessoas.size());
        Mockito.when(pessoaService.findAll(0, 10, "id", "asc", null))
                .thenReturn(pessoasPage);

        String json = objectMapper.writeValueAsString(pessoasPage);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(URL_PESSOA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request )
                .andExpect(status().isNoContent());

        assertTrue(pessoasPage.isEmpty());

    }

    @Test
    @DisplayName("deve retornar um page pessoa com status 200")
    void deveRetornarPagePessoaStatus200() throws Exception {
        PageRequest paginacao = PageRequest.of(0, 10);
        List<PessoaOutputDto> pessoas = List.of(PessoaStub.getPessoaOutputDto());
        Page<PessoaOutputDto> pessoasPage = new PageImpl(pessoas, paginacao, pessoas.size());
        Mockito.when(pessoaService.findAll(0, 10, "id", "asc", null))
                .thenReturn(pessoasPage);

        String json = objectMapper.writeValueAsString(pessoasPage);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(URL_PESSOA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isOk());

        assertFalse(pessoasPage.isEmpty());
        assertEquals("joão",pessoasPage.getContent().get(0).getNome());
        assertEquals(1,pessoasPage.getContent().size());

    }
}
