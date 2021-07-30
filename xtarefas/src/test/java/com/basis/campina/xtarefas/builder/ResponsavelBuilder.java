package com.basis.campina.xtarefas.builder;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.services.ResponsavelService;
import com.basis.campina.xtarefas.services.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.services.mapper.ResponsavelMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class ResponsavelBuilder extends ConstrutorDeEntidade<Responsavel> {

    @Autowired
    private ResponsavelService service;

    @Autowired
    private ResponsavelMapper mapper;


    @Override
    public Responsavel construirEntidade() throws ParseException {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome("Responsavel Teste");
        responsavel.setEmail("responsavel@teste.com");
        responsavel.setDataNascimento(LocalDate.now());

        return responsavel;
    }

    @Override
    protected Responsavel persistir(Responsavel entidade) {
        ResponsavelDTO responsavelDTO = service.adicionar(mapper.toDto(entidade));
        return mapper.toEntity(responsavelDTO);
    }

    @SneakyThrows
    public ResponsavelDTO construirDTO() {
        return mapper.toDto(persistir(construir()));
    }

    @Override
    public Responsavel obterPorId(Long id) {
        return null;
    }

    @Override
    public Collection<Responsavel> obterTodos() {
        return null;
    }
}
