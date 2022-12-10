package com.br.service.everson.libraryapi.api.dto.output;

import lombok.Data;

@Data
public class DocumentoOutputDto {

    private Long id;

    private String rg;

    private String cpf;

    private String tituloEleitor;
}
