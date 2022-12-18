package com.br.service.everson.libraryapi.api.book.controller.service;

import com.br.service.everson.libraryapi.api.dto.input.BookInputDto;
import com.br.service.everson.libraryapi.api.dto.output.BookOutputDto;
import com.br.service.everson.libraryapi.core.modelmapper.MapperConvert;
import com.br.service.everson.libraryapi.domain.repository.BookRepository;
import com.br.service.everson.libraryapi.domain.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository repository;

    @Autowired
    MapperConvert mapperConvert;

    @Test
    @DisplayName("deve salvar um livro")
    public void saveBookTest() {


        BookOutputDto bookOutputDto = bookService.create(createValidBook());

        assertTrue(!Objects.isNull(bookOutputDto.getId()));
        assertEquals(1L,bookOutputDto.getId());
        assertEquals(bookOutputDto.getTitle(),"as aventuras");
        assertEquals(bookOutputDto.getAuthor(),"fulano");


    }

    @Test
    @DisplayName("Deve lançar erro de negocio ao tentar salvar livro com mesmo titulo")
    public void shouldNotSaveABookWithDuplicatedTitle(){
//       TODO: 18/12/2022 Refactory all method
//       BookInputDto book = createValidBook();
//
//       Mockito.when(repository.existsByTitle(Mockito.anyString())).thenReturn(true);
//
//        Throwable exception = org.assertj.core.api.Assertions.catchThrowable(()-> service.create(book));
//        assertThat(BusinessException.class);
//        assertThat(exception)
//                .isInstanceOf(BusinessException.class)
//                .hasMessage("O titulo já existe em outro livro");
//
//        Mockito.verify(repository, Mockito.never()).save(book);
    }

    private BookInputDto createValidBook(){
        BookInputDto bookInputDto = BookInputDto.builder().author("fulano").title("as aventuras").build();
        return bookInputDto;
    }

}
