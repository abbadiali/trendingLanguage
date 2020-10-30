package com.gemography.test.model;

public class Page {
    private Repository[] items;

    public Page() {
    }

    public Page(Repository[] items) {
        this.items = items;
    }

    public Repository[] getItems() {
        return items;
    }

    public void setItems(Repository[] items) {
        this.items = items;
    }
}
