package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.services.AnexoService;
import com.basis.campina.xtarefas.services.dto.AnexoDTO;
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
@RequestMapping("/api/anexos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnexoResource {

    private final AnexoService service;

    @GetMapping()
    public ResponseEntity<List<AnexoDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<AnexoDTO> salvar(@RequestBody @Valid AnexoDTO anexoDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI("/api/anexos")).body(service.adicionar(anexoDTO));
    }

    @PutMapping
    public ResponseEntity<AnexoDTO> atualizar(@Valid @RequestBody AnexoDTO anexoDto) {
        AnexoDTO dto = service.adicionar(anexoDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }

}
