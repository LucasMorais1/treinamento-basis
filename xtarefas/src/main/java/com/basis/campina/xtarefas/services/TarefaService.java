package com.basis.campina.xtarefas.services;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.domain.elasticsearch.TarefaDocument;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.repository.elastic.TarefaSearchRepository;
import com.basis.campina.xtarefas.services.dto.TarefaDTO;
import com.basis.campina.xtarefas.services.event.TarefaEvent;
import com.basis.campina.xtarefas.services.exception.RegraNegocioException;
import com.basis.campina.xtarefas.services.filter.TarefaFilter;
import com.basis.campina.xtarefas.services.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaMapper mapper;

    private final TarefaRepository repository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final TarefaSearchRepository tarefaSearchRepository;

    public List<TarefaDTO> listar() {
        List<Tarefa> tarefas = this.repository.findAll();
        return this.mapper.toDto(tarefas);
    }

    public TarefaDTO obterPorId(Long id){
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Tarefa não encontrado"));
        return mapper.toDto(tarefa);
    }

    public TarefaDTO adicionar(TarefaDTO tarefaDto) {
        if (Objects.nonNull(tarefaDto.getId())) {
            verificaRepeticao(tarefaDto.getId());
        }
        Tarefa tarefa = mapper.toEntity(tarefaDto);
        Tarefa tarefaSalvo = repository.save(tarefa);
        applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        return mapper.toDto(tarefaSalvo);
    }

    public TarefaDTO remover(Long id) {
        Tarefa tarefa = this.mapper.toEntity(this.obterPorId(id));
        tarefaSearchRepository.deleteById(id);
        repository.delete(tarefa);
        return mapper.toDto(tarefa);
    }

    private void verificaRepeticao(Long id) {
        if (!repository.existsById(id))
            throw new RegraNegocioException("Id não encontrado!");
    }

    public Page<TarefaDocument> search(TarefaFilter tarefaFilter, Pageable pageable){
        return tarefaSearchRepository.search(tarefaFilter.getFilter(), pageable);
    }
}
