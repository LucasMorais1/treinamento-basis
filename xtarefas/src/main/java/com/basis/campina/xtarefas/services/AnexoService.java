package com.basis.campina.xtarefas.services;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.services.dto.AnexoDTO;
import com.basis.campina.xtarefas.services.dto.TarefaDTO;
import com.basis.campina.xtarefas.services.exception.RegraNegocioException;
import com.basis.campina.xtarefas.services.feign.DocumentClient;
import com.basis.campina.xtarefas.services.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoMapper mapper;

    private final AnexoRepository repository;

    private final TarefaService tarefaService;

    private final DocumentClient documentClient;

    public List<AnexoDTO> listar() {
        return mapper.toDto(repository.findAll());
    }

    public AnexoDTO obterPorId(Long id){
        Anexo anexo = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Anexo não encontrado"));
        anexo.setFile(documentClient.buscar(anexo.getUuid()).getFile());

        return mapper.toDto(anexo);
    }

    public AnexoDTO adicionar(AnexoDTO anexoDto) {
        if (Objects.nonNull(anexoDto.getId())) {
            verificaRepeticao(anexoDto.getId());
        }
        Anexo anexo = mapper.toEntity(anexoDto);
        anexoDto.setUuid(UUID.randomUUID().toString());
        documentClient.salvar(anexoDto);
        Anexo anexoSalvo = repository.save(anexo);

        TarefaDTO tarefaDTO = tarefaService.obterPorId(anexo.getTarefa().getId());
        tarefaService.adicionar(tarefaDTO);

        return mapper.toDto(anexoSalvo);
    }

    public AnexoDTO remover(Long id) {
        Anexo anexo = this.mapper.toEntity(this.obterPorId(id));
        repository.delete(anexo);
        documentClient.deletar(anexo.getUuid());
        return mapper.toDto(anexo);
    }

    private void verificaRepeticao(Long id) {
        if (!repository.existsById(id))
            throw new RegraNegocioException("Id não encontrado!");
    }

    public List<String> getNomeAnexosByTarefaId(Long id) {
        return repository.getNomeAnexosByTarefaId(id);
    }
}
