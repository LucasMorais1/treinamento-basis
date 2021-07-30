package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.builder.TarefaBuilder;
import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.services.TarefaService;
import com.basis.campina.xtarefas.services.dto.TarefaDTO;
import com.basis.campina.xtarefas.services.elastic.ElasticSearchService;
import com.basis.campina.xtarefas.services.event.TarefaEvent;
import com.basis.campina.xtarefas.services.filter.TarefaFilter;
import com.basis.campina.xtarefas.util.IntTestComum;
import com.basis.campina.xtarefas.util.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TarefaResourceIT extends IntTestComum {

    private final String API_TAREFAS = "/api/tarefas";
    private final String API_TAREFAS_ID = "/api/tarefas/{id}";

    private final String LISTAR_RESPONSAVEIS = API_TAREFAS + "/search";

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    @DisplayName("Salvar tarefa com sucesso")
    public void salvarTarefa() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.construirDTO();

        getMockMvc().perform(post(API_TAREFAS).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar tarefa por id com sucesso")
    public void buscarPorId() throws Exception {
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(get(API_TAREFAS_ID, tarefa.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(tarefa)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar tarefa com sucesso")
    public void editarTarefa() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.construirDTO();
        tarefaDTO.setNome("Tarefa nome editado");

        getMockMvc().perform(put(API_TAREFAS_ID, tarefaDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isOk());
    }


    @Test
    @Transactional
    @DisplayName("Listar tarefa com sucesso")
    public void listarResponsaveis() throws Exception {
        TarefaDTO tarefaDTO= this.tarefaBuilder.construirDTO();

        this.elasticSearchService.reindex();
        new TarefaEvent(tarefaDTO.getId());

        TarefaFilter filtro = new TarefaFilter();
        filtro.setQuery(tarefaDTO.getNome());

        getMockMvc().perform(post(LISTAR_RESPONSAVEIS).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(filtro)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Excluír tarefa com sucesso")
    public void excluirTarefa() throws Exception {
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(delete(API_TAREFAS_ID, tarefa.getId()))
                .andExpect(status().isOk());
    }
}
