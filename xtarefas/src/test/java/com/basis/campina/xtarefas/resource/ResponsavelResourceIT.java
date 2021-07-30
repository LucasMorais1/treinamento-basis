package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.builder.ResponsavelBuilder;
import com.basis.campina.xtarefas.config.containers.ContainersFactory;
import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.services.ResponsavelService;
import com.basis.campina.xtarefas.services.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.services.elastic.ElasticSearchService;
import com.basis.campina.xtarefas.services.event.ResponsavelEvent;
import com.basis.campina.xtarefas.services.filter.ResponsavelFilter;
import com.basis.campina.xtarefas.util.IntTestComum;
import com.basis.campina.xtarefas.util.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;

import static com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@ExtendWith(SpringExtension.class)
@Testcontainers
public class ResponsavelResourceIT extends IntTestComum {

    private final String API_RESPONSAVEL = "/api/responsaveis";
    private final String API_RESPONSAVEL_ID = "/api/responsaveis/{id}";

    private final String LISTAR_RESPONSAVEIS = API_RESPONSAVEL + "/search";


    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Container
    private static ContainersFactory containersFactory = ContainersFactory.getInstance();

    @Test
    @DisplayName("Salvar Responsável com sucesso")
    public void salvarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = responsavelBuilder.construirDTO();

        getMockMvc().perform(post(API_RESPONSAVEL).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(responsavelDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar responsável por id com sucesso")
    public void buscarPorId() throws Exception {
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(get(API_RESPONSAVEL_ID, responsavel.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(responsavel)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar responsável com sucesso")
    public void editarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = responsavelBuilder.construirDTO();
        responsavelDTO.setNome("Responsavel nome editado");

        getMockMvc().perform(put(API_RESPONSAVEL_ID, responsavelDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(responsavelDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Listar Responsável com sucesso")
    public void listarResponsaveis() throws Exception {
        ResponsavelDTO responsavelDTO= this.responsavelBuilder.construirDTO();

        this.elasticSearchService.reindex();
        new ResponsavelEvent(responsavelDTO.getId());

        ResponsavelFilter filtro = new ResponsavelFilter();
        filtro.setQuery(responsavelDTO.getNome());

        getMockMvc().perform(post(LISTAR_RESPONSAVEIS).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(filtro)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Excluír responsável com sucesso")
    public void excluirResponsavel() throws Exception {
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(delete(API_RESPONSAVEL_ID, responsavel.getId()))
                .andExpect(status().isOk());
    }
}


