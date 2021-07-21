package com.basis.campina.xtarefas.services.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class ResponsavelDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nome;

    @NotBlank
    @Size(max = 200)
    private String email;

    @NotNull
    private LocalDate dataNascimento;
}
