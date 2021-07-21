package com.basis.campina.xtarefas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "tb_responsavel")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_responsavel")
    @SequenceGenerator(name = "sq_responsavel", sequenceName = "sq_responsavel", allocationSize = 1)
    @Column(name = "co_responsavel")
    private Long id;

    @Column(name = "no_responsavel")
    private String nome;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;
}
