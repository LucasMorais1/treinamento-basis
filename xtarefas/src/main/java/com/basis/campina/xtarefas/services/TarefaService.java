package com.basis.campina.xtarefas.services;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.services.dto.TarefaDTO;
import com.basis.campina.xtarefas.services.exception.RegraNegocioException;
import com.basis.campina.xtarefas.services.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaMapper mapper;

    private final TarefaRepository repository;

    public List<TarefaDTO> listar() {
        List<Tarefa> tarefas = this.repository.findAll();
        return this.mapper.toDto(tarefas);
    }

    public TarefaDTO obterPorId(Long id){
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Tarefa não encontrado"));
        return mapper.toDto(tarefa);
    }

    public TarefaDTO adicionar(TarefaDTO tarefaDto) {
        if (tarefaDto.getId() != null) {
            verificaRepeticao(tarefaDto.getId());
        }
        Tarefa tarefa = mapper.toEntity(tarefaDto);
        Tarefa tarefaSalvo = repository.save(tarefa);
        return mapper.toDto(tarefaSalvo);
    }

    public TarefaDTO remover(Long id) {
        Tarefa tarefaParaRemover = this.mapper.toEntity(this.obterPorId(id));
        repository.delete(tarefaParaRemover);
        return mapper.toDto(tarefaParaRemover);
    }

    private void verificaRepeticao(Long id) {
        if (!repository.existsById(id))
            throw new RegraNegocioException("Id não encontrado!");
    }
}
