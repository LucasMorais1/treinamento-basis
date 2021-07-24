package com.basis.campina.xtarefas.services.elastic;

import com.basis.campina.xtarefas.domain.elasticsearch.ResponsavelDocument;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.repository.elastic.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.services.event.ResponsavelEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResponsavelSearchService {

    private final ResponsavelRepository responsavelRepository;

    private final ResponsavelSearchRepository responsavelSearchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(ResponsavelEvent event) {
        log.info("Iniciando Indexação de responsavel {}", event.getId());
        ResponsavelDocument responsavelDocument = this.responsavelRepository.getDocument(event.getId());
        this.responsavelSearchRepository.save(responsavelDocument);
    }
}
