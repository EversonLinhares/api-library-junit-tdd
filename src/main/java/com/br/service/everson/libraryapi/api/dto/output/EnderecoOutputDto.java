package com.br.service.everson.libraryapi.api.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoOutputDto {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String localidade;
    private String uf;
}
