package com.basis.campina.xtarefas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tarefa")
    @SequenceGenerator(name = "sq_tarefa", sequenceName = "sq_tarefa", allocationSize = 1)
    @Column(name = "co_tarefa")
    private Long id;

    @Column(name = "no_tarefa")
    private String nome;

    @Column(name = "dt_inicio_tarefa")
    private LocalDate dataInicio;

    @Column(name = "dt_conclusao_tarefa")
    private LocalDate dataConclusao;

    @Column(name = "st_tarefa")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_responsavel")
    private Responsavel responsavel;

    @OneToMany(mappedBy = "tarefa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anexo> anexos;
}
