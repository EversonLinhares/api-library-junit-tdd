package com.br.service.everson.libraryapi.api.dto.input;

import lombok.Data;

@Data
public class EnderecoInputDto {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String localidade;
    private String uf;
}
