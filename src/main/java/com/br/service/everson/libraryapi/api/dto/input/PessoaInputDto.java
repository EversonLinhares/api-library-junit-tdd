package com.br.service.everson.libraryapi.api.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaInputDto {

    private String nome;

    private String nomeSocial;

    private String nomeMae;

    private String nomePai;

    @Embedded
    private EnderecoInputDto endereco;

    @NotNull
    @Valid
    private DocumentoInputDto documento;
}
