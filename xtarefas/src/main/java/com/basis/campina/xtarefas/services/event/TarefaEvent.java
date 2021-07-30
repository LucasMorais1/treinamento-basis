package com.basis.campina.xtarefas.services.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TarefaEvent extends DefaultEvent {

    public TarefaEvent(Long id) {
        super(id);
    }
}
