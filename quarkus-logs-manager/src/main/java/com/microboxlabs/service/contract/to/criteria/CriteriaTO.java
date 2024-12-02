package com.microboxlabs.service.contract.to.criteria;

public class CriteriaTO {
    private String search;
    private int page;
    private int size;

    public CriteriaTO(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
