package com.br.service.everson.libraryapi.domain.service;

import com.br.service.everson.libraryapi.api.dto.input.DocumentoInputDto;
import com.br.service.everson.libraryapi.api.dto.input.PessoaInputDto;
import com.br.service.everson.libraryapi.api.dto.output.PessoaOutputDto;
import com.br.service.everson.libraryapi.core.modelmapper.MapperConvert;
import com.br.service.everson.libraryapi.domain.model.Documento;
import com.br.service.everson.libraryapi.domain.model.Pessoa;
import com.br.service.everson.libraryapi.domain.repository.DocumentoRepository;
import com.br.service.everson.libraryapi.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    MapperConvert mapperConvert;

    public PessoaOutputDto create (PessoaInputDto pessoaInputDto){
        Documento doc = mapperConvert.mapDtoToEntity(pessoaInputDto.getDocumento(), Documento.class);
        Documento documento = validarDocumento(doc);

        Optional<Pessoa> pessoa = pessoaRepository.buscarPeloCPF(doc.getCpf());

        if(pessoa.isEmpty()){
           pessoaInputDto.setDocumento(mapperConvert.mapEntityToDto(documento, DocumentoInputDto.class));
           Pessoa p = mapperConvert.mapDtoToEntity(pessoaInputDto, Pessoa.class);
           p = pessoaRepository.save(p);
           return mapperConvert.mapEntityToDto(p, PessoaOutputDto.class);
        }

        Pessoa pessoaCadastrada = pessoa.get();

        return mapperConvert.mapEntityToDto(pessoaCadastrada, PessoaOutputDto.class);
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

    public Page<PessoaOutputDto> findAll(int page,int size,String sortBy,String sortDir,String nome){

    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);

    Page<Pessoa> listaPessoas = pessoaRepository.findWithFilter(nome,pageable);

    Page<PessoaOutputDto> listaPessoasPaginadas = mapperConvert.mapEntityPageIntoDtoPage(listaPessoas, PessoaOutputDto.class);

        return listaPessoasPaginadas;
    }

    public PessoaOutputDto findById(Long id){
        return mapperConvert.mapEntityToDto(pessoaRepository.findById(id),PessoaOutputDto.class);
    }
}
