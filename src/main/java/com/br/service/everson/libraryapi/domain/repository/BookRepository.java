package com.br.service.everson.libraryapi.domain.repository;

import com.br.service.everson.libraryapi.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByTitle(String title);
}
