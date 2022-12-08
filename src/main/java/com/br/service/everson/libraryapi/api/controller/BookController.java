package com.br.service.everson.libraryapi.api.controller;

import com.br.service.everson.libraryapi.api.dto.output.BookRequestDto;
import com.br.service.everson.libraryapi.api.dto.output.BookResponseDto;
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
    public ResponseEntity<BookResponseDto> create(@RequestBody @Valid  BookRequestDto bookRequestDto) {
        Book entity = modelMapper.map(bookRequestDto, Book.class);
            entity = bookService.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(entity, BookResponseDto.class));

    }

    @GetMapping
    public ResponseEntity <List<BookResponseDto>> findAll(){
        List<Book> listaBooks = bookRepository.findAll();
        return ResponseEntity.ok().body(listaBooks.stream().map(b -> modelMapper.map(b, BookResponseDto.class)).collect(Collectors.toList()));
    }
}
