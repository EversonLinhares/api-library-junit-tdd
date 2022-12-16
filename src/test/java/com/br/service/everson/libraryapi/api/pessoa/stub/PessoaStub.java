package com.br.service.everson.libraryapi.api.pessoa.stub;

import com.br.service.everson.libraryapi.api.dto.input.DocumentoInputDto;
import com.br.service.everson.libraryapi.api.dto.input.EnderecoInputDto;
import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.DocumentoOutputDto;
import com.br.service.everson.libraryapi.api.dto.output.EnderecoOutputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;

public class PessoaStub {

    public static PessoaOutputDto getPessoaOutputDto(){
        return new PessoaOutputDto(1L,"nome","social","mae","pai",new EnderecoOutputDto(),new DocumentoOutputDto());
    }

    public static PessoaInputDto getPessoaInputDto(){
        return new PessoaInputDto("pessoa","social","mae","pai",new EnderecoInputDto(),new DocumentoInputDto());
    }
}
