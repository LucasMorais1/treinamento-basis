package com.basis.campina.xtarefas.services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "xdocumentos", url = "${application.feign.url-documents}")
public interface DocumentClient {

    @GetMapping("/api/documents")
    String getAll();
}
