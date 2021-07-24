package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.services.elastic.ElasticSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elasticsearch/reindex")
@AllArgsConstructor
public class ElasticsearchResource {

    private final ElasticSearchService elasticSearchService;

    @GetMapping
    public ResponseEntity<String> reindex() {
        this.elasticSearchService.reindex();
        return ResponseEntity.ok().body("Reindexando o elasticsearch");
    }

    @GetMapping("/{entity}")
    public ResponseEntity<String> reindex(@PathVariable String entity) {
        this.elasticSearchService.reindexEntity(entity);
        return ResponseEntity.ok().body("Reindexando o elasticsearch por entidade");
    }
}
