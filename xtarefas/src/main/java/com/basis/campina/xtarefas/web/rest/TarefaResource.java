package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.services.TarefaService;
import com.basis.campina.xtarefas.services.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TarefaResource {

    private final TarefaService service;

    @GetMapping()
    public ResponseEntity<List<TarefaDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> salvar(@RequestBody @Valid TarefaDTO tarefaDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI("/api/tarefas")).body(service.adicionar(tarefaDTO));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizar(@Valid @RequestBody TarefaDTO tarefaDto) {
        TarefaDTO dto = service.adicionar(tarefaDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }
}
