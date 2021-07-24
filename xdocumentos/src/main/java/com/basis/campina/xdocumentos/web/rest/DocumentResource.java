package com.basis.campina.xdocumentos.web.rest;

import com.basis.campina.xdocumentos.service.DocumentService;
import com.basis.campina.xdocumentos.service.dto.DocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentResource {

    private final DocumentService documentService;

    @GetMapping("/{uuid}")
    public ResponseEntity<DocumentDto> buscar(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok(documentService.getDocument(uuid));
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody DocumentDto documentDto){
        return ResponseEntity.ok(documentService.save(documentDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable("uuid") String uuid){
        documentService.deletar(uuid);
        return ResponseEntity.ok().build();
    }

}
