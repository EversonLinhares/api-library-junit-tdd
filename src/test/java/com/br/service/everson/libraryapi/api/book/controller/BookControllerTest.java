package com.br.service.everson.libraryapi.api.book.controller;


import com.br.service.everson.libraryapi.api.dto.input.BookInputDto;
import com.br.service.everson.libraryapi.api.dto.output.BookOutputDto;
import com.br.service.everson.libraryapi.domain.repository.BookRepository;
import com.br.service.everson.libraryapi.domain.service.BookService;
import com.br.service.everson.libraryapi.domain.service.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {

    static String BOOK_API = "/api/books";

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Deve criar livro com sucesso")
    public void createBookTest() throws Exception {

        BookInputDto bookInputDto = BookInputDto.builder()
                .author("arthur")
                .title("as aventuras").build();

        BookOutputDto savedBook = BookOutputDto.builder().id(1L).author("arthur").title("as aventuras").build();

        BDDMockito.given(bookService.create(Mockito.any(BookInputDto.class))).willReturn(savedBook);

        String json = mapper.writeValueAsString(bookInputDto);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value(bookInputDto.getTitle()))
                .andExpect(jsonPath("author").value(bookInputDto.getAuthor()))
        ;

    }


    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados necessarios")
    public void createInvalidBookTest() throws Exception {

        String json = mapper.writeValueAsString(new BookInputDto());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( status().isBadRequest())
                .andExpect( jsonPath("errors", hasSize(2)));
    }


    @Test
    @DisplayName("Deve lançar erro ao tentar cadastrar um livro com mesmo titulo de outro")
    public void createBookWithDuplicatedTitle() throws Exception{

        BookInputDto dto = createNewBook();
        String mensagemErro = "O titulo já existe em outro livro";
        String json = mapper.writeValueAsString(dto);
        BDDMockito.given(bookService.create(Mockito.any(BookInputDto.class)))
                .willThrow(new BusinessException(mensagemErro));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors",hasSize(1)))
                .andExpect(jsonPath("errors[0]")
                        .value(mensagemErro));
    }

    private BookInputDto createNewBook(){
        return BookInputDto.builder().author("arthur").title("as aventuras").build();
    }
}
