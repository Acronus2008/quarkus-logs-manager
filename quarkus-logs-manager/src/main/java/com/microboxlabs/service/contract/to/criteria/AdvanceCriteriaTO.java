package com.microboxlabs.service.contract.to.criteria;

import java.util.Map;

public class AdvanceCriteriaTO extends CriteriaTO {
    private Map<String, Object> fields;

    public AdvanceCriteriaTO(int page, int size) {
        super(page, size);
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }
}
