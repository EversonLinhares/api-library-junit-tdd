package com.br.service.everson.libraryapi.api.controller;

import com.br.service.everson.libraryapi.api.appconstants.VariableConst;
import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;
import com.br.service.everson.libraryapi.domain.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<PessoaOutputDto>> findAll(
            @RequestParam(value = "page",required = false,defaultValue = VariableConst.DEFAULT_NUMERO_PAGINA) int page,
            @RequestParam(value = "size",required = false,defaultValue = VariableConst.DEFAULT_TOTAL_PAGINA) int size,
            @RequestParam(value = "sortBy",required = false,defaultValue = VariableConst.DEFAULT_SORT_POR) String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = VariableConst.DEFAULT_SORT_DIRECAO) String sortDir,
            @RequestParam(value = "nome",required = false) String nome){
        Page<PessoaOutputDto> pessoas = pessoaService.findAll(page, size, sortBy, sortDir,nome);

        if (pessoas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok().body(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaOutputDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(pessoaService.findById(id));
    }

}
