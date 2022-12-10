package com.br.service.everson.libraryapi.api.book.controller.service;

import com.br.service.everson.libraryapi.domain.service.exception.BusinessException;
import com.br.service.everson.libraryapi.domain.model.Book;
import com.br.service.everson.libraryapi.domain.repository.BookRepository;
import com.br.service.everson.libraryapi.domain.service.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @BeforeEach
    public void setUp(){
        this.service = new BookService(repository);
    }

    @MockBean
    BookRepository repository;

    @MockBean
    ModelMapper modelMapper;


    @Test
    @DisplayName("deve salvar um livro")
    public void saveBookTest() {
        //cenario
        Book book = Book.builder().author("fulano").title("as aventuras").build();
        Mockito.when(repository.save(book)).thenReturn(
                Book.builder()
                        .id(1L)
                        .author("fulano")
                        .title("as aventuras").build()
        );

        //execucao
        Book savedBook = service.save(book);
        assertEquals(1L,savedBook.getId());
        //verificacao
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("as aventuras");
        assertThat(savedBook.getAuthor()).isEqualTo("fulano");


    }

    @Test
    @DisplayName("Deve lançar erro de negocio ao tentar salvar livro com mesmo titulo")
    public void shouldNotSaveABookWithDuplicatedTitle(){

       Book book = createValidBook();

       Mockito.when(repository.existsByTitle(Mockito.anyString())).thenReturn(true);

        Throwable exception = org.assertj.core.api.Assertions.catchThrowable(()-> service.save(book));
        assertThat(BusinessException.class);
        assertThat(exception)
                .isInstanceOf(BusinessException.class)
                .hasMessage("O titulo já existe em outro livro");

        Mockito.verify(repository, Mockito.never()).save(book);
    }


    private Book createValidBook(){
        Book validBook = Book.builder().author("fulano").title("as aventuras").build();
        return validBook;
    }

}
