package com.basis.campina.xdocumentos.service;

import com.basis.campina.xdocumentos.config.ApplicationProperties;
import com.basis.campina.xdocumentos.service.dto.DocumentDto;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public class DocumentService {

    private final MinioClient client;

    private final ApplicationProperties applicationProperties;

    @SneakyThrows
    private String save(DocumentDto documentoDTO) {
        client.putObject(PutObjectArgs.builder()
                .stream(new ByteArrayInputStream(documentoDTO.getFile().getBytes()),
                        documentoDTO.getFile().getBytes().length, 0)
                .contentType(StandardCharsets.UTF_8.toString())
                .bucket(applicationProperties.getBucket())
                .object(documentoDTO.getUuId())
                .build());
        return documentoDTO.getUuId();
    }

    @SneakyThrows
    public DocumentDto getDocument(String uuId) {
        GetObjectResponse file = client.getObject(GetObjectArgs.builder()
                .bucket(applicationProperties.getBucket())
                .object(uuId)
                .build());
        return new DocumentDto(uuId, IOUtils.toString(file, StandardCharsets.UTF_8.toString()));
    }
}
