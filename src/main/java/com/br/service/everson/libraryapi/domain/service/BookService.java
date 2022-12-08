package com.br.service.everson.libraryapi.domain.service;

import com.br.service.everson.libraryapi.domain.service.exception.BusinessException;
import com.br.service.everson.libraryapi.domain.model.Book;
import com.br.service.everson.libraryapi.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book save(Book book) {
       if(repository.existsByTitle(book.getTitle())){
           throw new BusinessException("O titulo já existe em outro livro");
       }
        return repository.save(book);
    }
}
