package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Anexo;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    @Query(value = "select a.file from Anexo a where a.tarefa.id = :id")
    List<String> getNomeAnexosByTarefaId(@Param("id") Long id);
}
