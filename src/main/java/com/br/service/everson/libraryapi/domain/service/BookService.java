package com.br.service.everson.libraryapi.domain.service;

import com.br.service.everson.libraryapi.api.dto.input.BookInputDto;
import com.br.service.everson.libraryapi.api.dto.output.BookOutputDto;
import com.br.service.everson.libraryapi.core.modelmapper.MapperConvert;
import com.br.service.everson.libraryapi.domain.service.exception.BusinessException;
import com.br.service.everson.libraryapi.domain.model.Book;
import com.br.service.everson.libraryapi.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    @Autowired
    MapperConvert mapperConvert;

    public BookOutputDto create(BookInputDto bookInputDto) {
       if(repository.existsByTitle(bookInputDto.getTitle())){
           throw new BusinessException("O titulo já existe em outro livro");
       }

       Book book = repository.save(mapperConvert.mapDtoToEntity(bookInputDto,Book.class));

        return mapperConvert.mapEntityToDto(book, BookOutputDto.class);
    }

    public List<BookOutputDto> findAll() {
        return mapperConvert.collectionToDto(repository.findAll(),BookOutputDto.class);
    }
}
