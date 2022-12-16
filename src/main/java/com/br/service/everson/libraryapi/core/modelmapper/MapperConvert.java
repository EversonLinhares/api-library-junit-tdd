package com.br.service.everson.libraryapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperConvert {

    @Autowired
    ModelMapper modelMapper;

    public <D, E> Page<D> mapEntityPageIntoDtoPage(Page<E> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }
    public <D, E> D mapEntityToDto(E entities, Class<D> dtoClass){
        return modelMapper.map(entities, dtoClass);
    }

    public <D, E> E mapDtoToEntity(D dto, Class<E> entitiesClass){
        return modelMapper.map(dto,entitiesClass);
    }

    public <D, E> List<D> collectionToDto(List<E> listEntities, Class<D> dtoClass){
        return listEntities.stream().map(l -> mapEntityToDto(l,dtoClass)).collect(Collectors.toList());
    }


}
