package com.br.service.everson.libraryapi.api.controller;

import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;
import com.br.service.everson.libraryapi.domain.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaOutputDto> Create (@Valid @RequestBody PessoaInputDto pessoa){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.create(pessoa));
    }

}
