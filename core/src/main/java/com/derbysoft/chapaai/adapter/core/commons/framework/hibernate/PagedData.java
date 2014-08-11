package com.derbysoft.chapaai.adapter.core.commons.framework.hibernate;

import java.util.List;

public class PagedData<T> {
    private List<T> data;
    private long total;

    public PagedData(List<T> data, long total) {
        this.data = data;
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public long getTotal() {
        return total;
    }
}
