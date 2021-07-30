package com.basis.campina.xtarefas.config.containers;

import org.testcontainers.junit.jupiter.Container;

import java.util.Objects;

public class ContainersFactory {

    @Container
    private static ContainersFactory containersFactory;

    @Container
    private static CustomElasticContainer elasticSearchFactory;

    public static ContainersFactory getInstance() {
        if (Objects.isNull(elasticSearchFactory)) {
            elasticSearchFactory = ElasticsearchFactory.getInstance();
        }
        if (Objects.isNull(containersFactory)) {
            containersFactory = new ContainersFactory();
        }
        return containersFactory;
    }
}
