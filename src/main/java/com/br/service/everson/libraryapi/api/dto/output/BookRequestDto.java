package com.br.service.everson.libraryapi.api.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
}
