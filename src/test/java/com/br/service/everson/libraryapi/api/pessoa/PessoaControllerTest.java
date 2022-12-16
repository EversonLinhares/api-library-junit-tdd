package com.br.service.everson.libraryapi.api.pessoa;

import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;
import com.br.service.everson.libraryapi.api.pessoa.stub.PessoaStub;
import com.br.service.everson.libraryapi.domain.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PessoaControllerTest {

    static String PESSOA = "/pessoa";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PessoaService pessoaService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Deve cadastrar uma pessoa e retornar PessoaOutputDto com status 201 create")
    void deveCadastrarPessoaRertornarDtoComStatus201() throws Exception {
        PessoaOutputDto pessoa = PessoaStub.getPessoaOutputDto();

        Mockito.when(pessoaService.create(Mockito.any(PessoaInputDto.class))).thenReturn(pessoa);

        String json = objectMapper.writeValueAsString(pessoa);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PESSOA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("nome").value(pessoa.getNome()));

    }

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
