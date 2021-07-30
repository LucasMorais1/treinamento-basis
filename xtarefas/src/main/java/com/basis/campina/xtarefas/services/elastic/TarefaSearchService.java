package com.basis.campina.xtarefas.services.elastic;

import com.basis.campina.xtarefas.domain.elasticsearch.TarefaDocument;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.repository.elastic.TarefaSearchRepository;
import com.basis.campina.xtarefas.services.AnexoService;
import com.basis.campina.xtarefas.services.event.TarefaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TarefaSearchService {

    private final TarefaRepository tarefaRepository;

    private final AnexoService anexoService;

    private final TarefaSearchRepository tarefaSearchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(TarefaEvent event) {
        log.info("Iniciando Indexação de tarefa {}", event.getId());
        TarefaDocument tarefaDocument = this.tarefaRepository.getDocument(event.getId());
        String nomeAnexos = anexoService.getNomeAnexosByTarefaId(event.getId()).stream().collect(Collectors.joining(", "));
        tarefaDocument.setNomeAnexos(nomeAnexos);
        this.tarefaSearchRepository.save(tarefaDocument);
    }
}
