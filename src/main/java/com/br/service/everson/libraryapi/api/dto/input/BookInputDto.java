package com.br.service.everson.libraryapi.api.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInputDto {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
}
