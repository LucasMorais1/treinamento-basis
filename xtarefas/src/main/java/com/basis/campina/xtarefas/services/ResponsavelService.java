package com.basis.campina.xtarefas.services;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.services.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.services.exception.RegraNegocioException;
import com.basis.campina.xtarefas.services.feign.DocumentClient;
import com.basis.campina.xtarefas.services.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ResponsavelService {
    
    private final ResponsavelMapper mapper;

    private final ResponsavelRepository repository;

    private final DocumentClient documentClient;

    public List<ResponsavelDTO> listar() {
        return this.mapper.toDto(this.repository.findAll());
    }

    public ResponsavelDTO obterPorId(Long id){
        Responsavel responsavel = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Responsavel não encontrado"));
        return mapper.toDto(responsavel);
    }

    public ResponsavelDTO adicionar(ResponsavelDTO responsavelDto) {
        if (responsavelDto.getId() != null) {
            verificaRepeticao(responsavelDto.getId());
        }
        Responsavel responsavel = mapper.toEntity(responsavelDto);
        Responsavel responsavelSalvo = repository.save(responsavel);
        return mapper.toDto(responsavelSalvo);
    }

    public ResponsavelDTO remover(Long id) {
        Responsavel responsavelParaRemover = this.mapper.toEntity(this.obterPorId(id));
        repository.delete(responsavelParaRemover);
        return mapper.toDto(responsavelParaRemover);
    }

    private void verificaRepeticao(Long id) {
        if (!repository.existsById(id))
            throw new RegraNegocioException("Id não encontrado!");
    }

    public String getFeingTest() {
        return this.documentClient.getAll();
    }
}
