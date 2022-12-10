package com.br.service.everson.libraryapi.api.dto.output;

import lombok.Data;

import javax.persistence.Embedded;

@Data
public class PessoaOutputDto {

    private String nome;

    private String nomeSocial;

    private String nomeMae;

    private String nomePai;

    @Embedded
    private EnderecoOutputDto endereco;

    private DocumentoOutputDto documento;
}
