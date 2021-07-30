package com.basis.campina.xtarefas.services.mapper;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.services.dto.TarefaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ResponsavelMapper.class, AnexoMapper.class})
public interface TarefaMapper {

    @Mapping(source = "responsavel.id", target = "responsavelId")
    TarefaDTO toDto(Tarefa tarefa);

    @Mapping(source = "responsavelId", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    @Mapping(source = "responsavel.id", target = "responsavelId")
    List<TarefaDTO> toDto(List<Tarefa> tarefas);

    @Mapping(source = "responsavelId", target = "responsavel.id")
    List<Tarefa> toEntity(List<TarefaDTO> tarefasDTO);
}
