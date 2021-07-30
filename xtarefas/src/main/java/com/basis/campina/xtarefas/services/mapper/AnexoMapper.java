package com.basis.campina.xtarefas.services.mapper;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.services.dto.AnexoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TarefaMapper.class})
public interface AnexoMapper {

    @Mapping(target = "tarefaId", source = "tarefa.id")
    AnexoDTO toDto(Anexo anexo);

    @Mapping(target = "tarefa.id", source = "tarefaId")
    Anexo toEntity(AnexoDTO anexoDTO);

    @Mapping(target = "tarefaId", source = "tarefa.id")
    List<AnexoDTO> toDto(List<Anexo> anexos);

    @Mapping(target = "tarefa.id", source = "tarefaId")
    List<Anexo> toEntity(List<AnexoDTO> anexosDTO);

}
