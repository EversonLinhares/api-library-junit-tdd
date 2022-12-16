package com.br.service.everson.libraryapi.api.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaOutputDto {

    private Long id;

    private String nome;

    private String nomeSocial;

    private String nomeMae;

    private String nomePai;

    @Embedded
    private EnderecoOutputDto endereco;

    private DocumentoOutputDto documento;
}
