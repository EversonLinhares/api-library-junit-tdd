package com.br.service.everson.libraryapi.domain.service;

import com.br.service.everson.libraryapi.api.dto.input.DocumentoInputDto;
import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;
import com.br.service.everson.libraryapi.domain.model.Documento;
import com.br.service.everson.libraryapi.domain.model.Pessoa;
import com.br.service.everson.libraryapi.domain.repository.DocumentoRepository;
import com.br.service.everson.libraryapi.domain.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    public PessoaOutputDto create (PessoaInputDto pessoaInputDto){
        Documento doc = modelMapper.map(pessoaInputDto.getDocumento(), Documento.class);
        Documento documento = validarDocumento(doc);

        Optional<Pessoa> pessoa = pessoaRepository.buscarPeloCPF(doc.getCpf());

        if(pessoa.isEmpty()){
           pessoaInputDto.setDocumento(modelMapper.map(documento, DocumentoInputDto.class));
           Pessoa p = modelMapper.map(pessoaInputDto, Pessoa.class);
           p = pessoaRepository.save(p);
           return modelMapper.map(p, PessoaOutputDto.class);
        }

        Pessoa pessoaCadastrada = pessoa.get();

        return modelMapper.map(pessoaCadastrada, PessoaOutputDto.class);
    }

    @Transactional
    public Documento validarDocumento(Documento documentoInput) {
        Optional<Documento> documento = documentoRepository.buscarPorCPF(documentoInput.getCpf());

        if (documento.isPresent()) return documento.get();

        Documento novoDocumento = new Documento();
        novoDocumento.setCpf(documentoInput.getCpf());
        novoDocumento.setRg(documentoInput.getRg());
        novoDocumento.setTituloEleitor(documentoInput.getTituloEleitor());

        return novoDocumento;
    }
}
