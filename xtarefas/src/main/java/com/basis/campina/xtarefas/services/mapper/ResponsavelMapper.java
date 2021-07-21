package com.basis.campina.xtarefas.services.mapper;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.services.dto.ResponsavelDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper {

    ResponsavelDTO toDto(Responsavel responsavel);

    Responsavel toEntity(ResponsavelDTO responsavelDTO);

    List<ResponsavelDTO> toDto(List<Responsavel> responsavels);

    List<Responsavel> toEntity(List<ResponsavelDTO> responsavelsDTO);

}
