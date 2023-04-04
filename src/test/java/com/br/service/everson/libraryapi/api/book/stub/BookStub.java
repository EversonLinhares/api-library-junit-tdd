package com.br.service.everson.libraryapi.api.book.stub;

import com.br.service.everson.libraryapi.api.dto.input.BookInputDto;
import com.br.service.everson.libraryapi.api.dto.output.BookOutputDto;
import com.br.service.everson.libraryapi.domain.model.Book;

public class BookStub {


    public static final Book createValidBook(){
        return Book.builder().id(1L).title("as aventuras").author("fulano").build();
    }

    public static final BookOutputDto returnBookOutputDto(){
        return BookOutputDto.builder().id(1L).title("as aventuras").author("fulano").build();
    }

    public static final BookInputDto createValidBookInputDto(){
        return BookInputDto.builder().title("as aventuras").author("fulano").build();
    }
}
