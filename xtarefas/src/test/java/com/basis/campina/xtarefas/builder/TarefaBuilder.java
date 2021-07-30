package com.basis.campina.xtarefas.builder;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.services.TarefaService;
import com.basis.campina.xtarefas.services.dto.TarefaDTO;
import com.basis.campina.xtarefas.services.mapper.TarefaMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class TarefaBuilder extends ConstrutorDeEntidade<Tarefa> {

    @Autowired
    private TarefaService service;

    @Autowired
    private TarefaMapper mapper;

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Override
    public Tarefa construirEntidade() throws ParseException {
        Responsavel responsavel = responsavelBuilder.construir();

        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Tarefa Teste");
        tarefa.setDataInicio(LocalDate.of(2019, 11, 11));
        tarefa.setDataConclusao(LocalDate.of(2020, 11, 11));
        tarefa.setStatus("Codificando");
        tarefa.setResponsavel(responsavel);

        return tarefa;
    }

    @Override
    protected Tarefa persistir(Tarefa entidade) {
        TarefaDTO tarefaDTO = service.adicionar(mapper.toDto(entidade));
        return mapper.toEntity(tarefaDTO);
    }

    @SneakyThrows
    public TarefaDTO construirDTO() {
        return mapper.toDto(persistir(construir()));
    }

    @Override
    public Tarefa obterPorId(Long id) {
        return null;
    }

    @Override
    public Collection<Tarefa> obterTodos() {
        return null;
    }
}
