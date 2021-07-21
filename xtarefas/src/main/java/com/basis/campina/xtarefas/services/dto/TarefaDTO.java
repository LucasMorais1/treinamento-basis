package com.basis.campina.xtarefas.services.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TarefaDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nome;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataConclusao;

    @NotBlank
    @Size(max = 100)
    private String status;

    private List<AnexoDTO> anexos;

    private Long responsavelId;
}
