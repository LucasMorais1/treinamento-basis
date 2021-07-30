package com.basis.campina.xtarefas.services;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.elasticsearch.ResponsavelDocument;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.repository.elastic.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.services.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.services.event.ResponsavelEvent;
import com.basis.campina.xtarefas.services.exception.RegraNegocioException;
import com.basis.campina.xtarefas.services.filter.ResponsavelFilter;
import com.basis.campina.xtarefas.services.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ResponsavelService {
    
    private final ResponsavelMapper mapper;

    private final ResponsavelRepository repository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final ResponsavelSearchRepository responsavelSearchRepository;

    public List<ResponsavelDTO> listar() {
        return this.mapper.toDto(this.repository.findAll());
    }

    @Transactional(readOnly = true)
    public Page<ResponsavelDocument> filtrarPaginado(List<Long> ids, Pageable pageable) {
        return repository.filtrarPaginado(ids, CollectionUtils.isEmpty(ids), pageable);
    }

    public ResponsavelDTO obterPorId(Long id){
        Responsavel responsavel = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Responsavel não encontrado"));
        return mapper.toDto(responsavel);
    }

    public ResponsavelDTO adicionar(ResponsavelDTO responsavelDto) {
        if (Objects.nonNull(responsavelDto.getId())) {
            verificaRepeticao(responsavelDto.getId());
        }
        responsavelDto.setNome(responsavelDto.getNome());
        Responsavel responsavel = mapper.toEntity(responsavelDto);
        Responsavel responsavelSalvo = repository.save(responsavel);
        applicationEventPublisher.publishEvent(new ResponsavelEvent(responsavel.getId()));
        return mapper.toDto(responsavelSalvo);
    }

    public ResponsavelDTO remover(Long id) {
        Responsavel responsavel = this.mapper.toEntity(this.obterPorId(id));
        responsavelSearchRepository.deleteById(id);
        repository.delete(responsavel);
        return mapper.toDto(responsavel);
    }

    private void verificaRepeticao(Long id) {
        if (!repository.existsById(id))
            throw new RegraNegocioException("Id não encontrado!");
    }

    public Page<ResponsavelDocument> search(ResponsavelFilter responsavelFilter, Pageable pageable){
        return responsavelSearchRepository.search(responsavelFilter.getFilter(), pageable);
    }
}
