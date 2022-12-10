package com.br.service.everson.libraryapi.api.pessoa.stub;

import com.br.service.everson.libraryapi.domain.model.Documento;
import com.br.service.everson.libraryapi.domain.model.Pessoa;

public class PessoaStub {

    public static Pessoa getPessoa(){
        return new Pessoa(1L,"pessoa1","social1","mae1","pai1",null,new Documento());
    }
}
