package com.basis.campina.xtarefas.services.filter;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TarefaFilter extends DefaultFilter implements BaseFilter, Serializable {

    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "nome", "status", "nomeResponsavel");
        addShouldTermQuery(queryBuilder, "dataConclusao", query);
        addShouldTermQuery(queryBuilder, "dataInicio", query);

        return  queryBuilder;
    }
}
