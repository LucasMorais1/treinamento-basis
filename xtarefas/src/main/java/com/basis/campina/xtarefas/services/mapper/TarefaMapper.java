package com.basis.campina.xtarefas.services.mapper;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.services.dto.TarefaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ResponsavelMapper.class, AnexoMapper.class})
public interface TarefaMapper {

    TarefaDTO toDto(Tarefa tarefa);

    Tarefa toEntity(TarefaDTO tarefaDTO);

    List<TarefaDTO> toDto(List<Tarefa> tarefas);

    List<Tarefa> toEntity(List<TarefaDTO> tarefasDTO);
}
