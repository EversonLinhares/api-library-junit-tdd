package com.br.service.everson.libraryapi.api.dto.input;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class DocumentoInputDto {


    @CPF
    private String cpf;

    private String rg;

    private String tituloEleitor;

}
