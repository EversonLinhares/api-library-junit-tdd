package com.br.service.everson.libraryapi.api.controller;


import com.br.service.everson.libraryapi.api.dto.BookRequestDto;
import com.br.service.everson.libraryapi.domain.model.Book;
import com.br.service.everson.libraryapi.domain.service.BookService;
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

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Deve criar livro com sucesso")
    public void createBookTest() throws Exception {

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .author("arthur")
                .title("as aventuras").build();
        Book savedBook = Book.builder().id(1L).author("arthur").title("as aventuras").build();
        String json = mapper.writeValueAsString(bookRequestDto);

        BDDMockito.given(bookService.save(Mockito.any(Book.class))).willReturn(savedBook);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("title").value(bookRequestDto.getTitle()))
                .andExpect(jsonPath("author").value(bookRequestDto.getAuthor()))
        ;

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados necessarios")
    public void createInvalidBookTest(){

    }
}
