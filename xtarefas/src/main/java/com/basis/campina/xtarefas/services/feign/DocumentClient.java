package com.basis.campina.xtarefas.services.feign;

import com.basis.campina.xtarefas.services.dto.AnexoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "xdocumentos", url = "${application.feign.url-documents}/api/documents")
public interface DocumentClient {

    @GetMapping("/{uuid}")
    AnexoDTO buscar(@PathVariable("uuid") String uuid);

    @PostMapping
    String salvar(AnexoDTO anexoDTO);

    @DeleteMapping("/{uuid}")
    Void deletar(@PathVariable("uuid") String uuid);

}
