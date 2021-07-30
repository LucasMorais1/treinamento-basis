package com.basis.campina.xtarefas.services.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AnexoDTO {

    private Long id;

    @NotBlank
    private String file;

    @NotBlank
    private String uuid;

    @NotBlank
    @Size(max = 200)
    private String nome;

    private Long tarefaId;
}
