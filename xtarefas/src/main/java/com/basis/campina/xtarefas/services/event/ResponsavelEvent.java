package com.basis.campina.xtarefas.services.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponsavelEvent extends DefaultEvent {

    public ResponsavelEvent(Long id) {
        super(id);
    }
}
