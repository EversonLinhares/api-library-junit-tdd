package com.br.service.everson.libraryapi.api.controller;


import com.br.service.everson.libraryapi.api.dto.input.BookRequestDto;
import com.br.service.everson.libraryapi.domain.model.Book;
import com.br.service.everson.libraryapi.domain.repository.BookRepository;
import com.br.service.everson.libraryapi.domain.service.BookService;
import com.br.service.everson.libraryapi.domain.service.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
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

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .author("arthur")
                .title("as aventuras").build();

        Book savedBook = Book.builder().id(1L).author("arthur").title("as aventuras").build();

        BDDMockito.given(bookService.save(Mockito.any(Book.class))).willReturn(savedBook);

        String json = mapper.writeValueAsString(bookRequestDto);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value(bookRequestDto.getTitle()))
                .andExpect(jsonPath("author").value(bookRequestDto.getAuthor()))
        ;

    }


    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados necessarios")
    public void createInvalidBookTest() throws Exception {

        String json = mapper.writeValueAsString(new BookRequestDto());

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

        BookRequestDto dto = createNewBook();
        String mensagemErro = "O titulo já existe em outro livro";
        String json = mapper.writeValueAsString(dto);
        BDDMockito.given(bookService.save(Mockito.any(Book.class)))
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

    private BookRequestDto createNewBook(){
        return BookRequestDto.builder().author("arthur").title("as aventuras").build();
    }
}
