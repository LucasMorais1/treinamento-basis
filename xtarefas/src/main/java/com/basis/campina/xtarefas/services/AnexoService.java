package com.basis.campina.xtarefas.services;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.services.dto.AnexoDTO;
import com.basis.campina.xtarefas.services.exception.RegraNegocioException;
import com.basis.campina.xtarefas.services.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoMapper mapper;

    private final AnexoRepository repository;

    public List<AnexoDTO> listar() {
        return this.mapper.toDto(this.repository.findAll());
    }

    public AnexoDTO obterPorId(Long id){
        Anexo anexo = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Anexo não encontrado"));
        return mapper.toDto(anexo);
    }

    public AnexoDTO adicionar(AnexoDTO anexoDto) {
        if (anexoDto.getId() != null) {
            verificaRepeticao(anexoDto.getId());
        }
        Anexo anexo = mapper.toEntity(anexoDto);
        Anexo anexoSalvo = repository.save(anexo);
        return mapper.toDto(anexoSalvo);
    }

    public AnexoDTO remover(Long id) {
        Anexo anexoParaRemover = this.mapper.toEntity(this.obterPorId(id));
        repository.delete(anexoParaRemover);
        return mapper.toDto(anexoParaRemover);
    }

    private void verificaRepeticao(Long id) {
        if (!repository.existsById(id))
            throw new RegraNegocioException("Id não encontrado!");
    }
}
