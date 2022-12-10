package com.br.service.everson.libraryapi;

import com.br.service.everson.libraryapi.domain.model.Documento;
import com.br.service.everson.libraryapi.domain.model.Endereco;
import com.br.service.everson.libraryapi.domain.model.Pessoa;
import com.br.service.everson.libraryapi.domain.repository.DocumentoRepository;
import com.br.service.everson.libraryapi.domain.repository.PessoaRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
public
class LibraryApiApplicationTests {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    protected static Faker faker = Faker.instance(new Locale("pt", "BR"));

    protected static final String CPF = "14816433041";

    protected Documento criacaoDeDocumentoParaTeste(){
        Documento documento = new Documento();
        documento.setCpf(CPF);
        return documento;
    }

    protected Pessoa criacaoDePessoaParaTeste(Documento doc) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(faker.name().fullName());
        pessoa.setDocumento(doc);
        pessoa.setEndereco(criacaoDeEnderecoParaTeste());
        return pessoa;
    }
    protected Endereco criacaoDeEnderecoParaTeste(){
        Endereco endereco = new Endereco(faker.address().streetAddress(),faker.address().streetAddressNumber(),"rua1",faker.address().streetName(),
                faker.address().zipCode(),faker.address().city(),"RJ");
        return endereco;
    }

}
