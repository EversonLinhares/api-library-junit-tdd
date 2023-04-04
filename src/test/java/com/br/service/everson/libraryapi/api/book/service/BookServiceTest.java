package com.br.service.everson.libraryapi.api.book.service;

import com.br.service.everson.libraryapi.api.book.stub.BookStub;
import com.br.service.everson.libraryapi.api.dto.output.BookOutputDto;
import com.br.service.everson.libraryapi.core.modelmapper.MapperConvert;
import com.br.service.everson.libraryapi.domain.repository.BookRepository;
import com.br.service.everson.libraryapi.domain.service.BookService;
import com.br.service.everson.libraryapi.domain.service.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository repository;

    @Mock
    MapperConvert mapperConvert;

    @Test
    @DisplayName("deve salvar um livro")
    public void saveBookTest() {
        when(repository.save(BookStub.createValidBook())).thenReturn(BookStub.createValidBook());
        when(bookService.create(BookStub.createValidBookInputDto())).thenReturn(BookStub.returnBookOutputDto());

        BookOutputDto bookOutputDto = bookService.create(BookStub.createValidBookInputDto());

        assertTrue(!Objects.isNull(bookOutputDto.getId()));
        assertTrue(2L != bookOutputDto.getId());
        assertEquals(1L,bookOutputDto.getId());
        assertEquals(bookOutputDto.getTitle(),"as aventuras");
        assertEquals(bookOutputDto.getAuthor(),"fulano");


    }

    @Test
    @DisplayName("Deve lançar erro de negocio ao tentar salvar livro com mesmo titulo")
    public void shouldNotSaveABookWithDuplicatedTitle(){
//      outra forma de lançar erro abaixo .
//      when(bookService.create(BookStub.createValidBookInputDto())).thenThrow(new BusinessException("O titulo já existe em outro livro"));
        when(repository.existsByTitle(Mockito.anyString())).thenReturn(true);
       BusinessException exception = assertThrows(BusinessException.class, () -> bookService.create(BookStub.createValidBookInputDto()));

       assertEquals("O titulo já existe em outro livro", exception.getMessage());
       verify(repository, Mockito.never()).save(BookStub.createValidBook());
    }

}
