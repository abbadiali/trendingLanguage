package com.gemography.test.model;

public class Repository {
    private String url;
    private String language;

    public Repository() {
    }

    public Repository(String url, String language) {
        this.url = url;
        this.language = language;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "url='" + url + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
