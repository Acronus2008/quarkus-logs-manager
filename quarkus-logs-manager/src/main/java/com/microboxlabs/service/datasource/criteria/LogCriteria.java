package com.microboxlabs.service.datasource.criteria;

import com.microboxlabs.service.datasource.domain.Log;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.tuples.Tuple2;

import java.util.Map;

public class LogCriteria extends AdvanceCriteria<PanacheEntityBase> {

    @Override
    public PanacheQuery<PanacheEntityBase> query() {
        Tuple2<String, Parameters> objects = prepareQuery(super.getFields());
        return Log
                .find(objects.getItem1(), objects.getItem2())
                .page(super.getPage(), super.getSize());
    }

    private static String createPredicates(Map<String, Object> fields) {
        var predicates = "from Log l where 1=1";
        if (fields.containsKey("logLevel"))
            predicates += " and l.logLevel = :logLevel";
        if (fields.containsKey("serviceName"))
            predicates += " and l.serviceName = :serviceName";
        if (fields.containsKey("startDate"))
            predicates += " and l.timestamp >= :startDate";
        if (fields.containsKey("endDate"))
            predicates += " and l.timestamp <= :endDate";
        return predicates;
    }

    private static Tuple2<String, Parameters> prepareQuery(Map<String, Object> fields) {
        var parameters = new Parameters();
        final var params = fields
                .entrySet()
                .stream()
                .map(entry -> parameters.and(entry.getKey(), entry.getValue()))
                .reduce((p1, p2) -> p2)
                .orElse(Parameters.with("serviceName", ""));
        final var predicates = createPredicates(fields);
        return Tuple2.of(predicates, params);
    }
}
