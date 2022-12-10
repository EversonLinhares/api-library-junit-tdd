package com.br.service.everson.libraryapi.api.controller;

import com.br.service.everson.libraryapi.api.dto.input.BookInputDto;
import com.br.service.everson.libraryapi.api.dto.output.BookOutputDto;
import com.br.service.everson.libraryapi.domain.model.Book;
import com.br.service.everson.libraryapi.domain.repository.BookRepository;
import com.br.service.everson.libraryapi.domain.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookOutputDto> create(@RequestBody @Valid BookInputDto bookInputDto) {
        Book entity = modelMapper.map(bookInputDto, Book.class);
            entity = bookService.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(entity, BookOutputDto.class));

    }

    @GetMapping
    public ResponseEntity <List<BookOutputDto>> findAll(){
        List<Book> listaBooks = bookRepository.findAll();
        return ResponseEntity.ok().body(listaBooks.stream().map(b -> modelMapper.map(b, BookOutputDto.class)).collect(Collectors.toList()));
    }
}
