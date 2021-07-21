package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.services.ResponsavelService;
import com.basis.campina.xtarefas.services.dto.ResponsavelDTO;
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
@RequestMapping("/api/responsaveis")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResponsavelResource {

    private final ResponsavelService service;

    @GetMapping()
    public ResponseEntity<List<ResponsavelDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @GetMapping("/feing")
    public ResponseEntity<String> obterPorId(){
        return ResponseEntity.ok(service.getFeingTest());
    }

    @PostMapping
    public ResponseEntity<ResponsavelDTO> salvar(@RequestBody @Valid ResponsavelDTO responsavelDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI("/api/responsaveis")).body(service.adicionar(responsavelDTO));
    }

    @PutMapping
    public ResponseEntity<ResponsavelDTO> atualizar(@Valid @RequestBody ResponsavelDTO responsavelDto) {
        ResponsavelDTO dto = service.adicionar(responsavelDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }
}
