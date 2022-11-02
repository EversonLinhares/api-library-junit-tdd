package com.br.service.everson.libraryapi.api.controller;

import com.br.service.everson.libraryapi.api.dto.BookRequestDto;
import com.br.service.everson.libraryapi.domain.model.Book;
import com.br.service.everson.libraryapi.domain.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody BookRequestDto bookRequestDto) {
        Book entity = Book.builder().author(bookRequestDto.getAuthor())
                .title(bookRequestDto.getTitle()).build();
       entity = bookService.save(entity);
        return entity;
    }
}
