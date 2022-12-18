package com.br.service.everson.libraryapi.api.controller;

import com.br.service.everson.libraryapi.api.dto.input.BookInputDto;
import com.br.service.everson.libraryapi.api.dto.output.BookOutputDto;
import com.br.service.everson.libraryapi.domain.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookOutputDto> create(@RequestBody @Valid BookInputDto bookInputDto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookInputDto));
    }

    @GetMapping
    public ResponseEntity <List<BookOutputDto>> findAll(){
        return ResponseEntity.ok().body(bookService.findAll());
    }
}
