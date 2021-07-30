package com.basis.campina.xtarefas.config.containers;

import org.testcontainers.junit.jupiter.Container;

import java.util.Objects;

public class ElasticsearchFactory {

    @Container
    private static CustomElasticContainer container;

    public static CustomElasticContainer getInstance() {
        if (Objects.isNull(container)) {
            container = new CustomElasticContainer();
            container.start();
        }
        return container;
    }
}
