package com.basis.campina.xtarefas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "tb_anexo")
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_anexo")
    @SequenceGenerator(name = "sq_anexo", sequenceName = "sq_anexo", allocationSize = 1)
    @Column(name = "co_anexo")
    private Long id;

    @Column(name = "file")
    private String file;

    @Column(name = "no_anexo")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_tarefa")
    private Tarefa tarefa;

}
