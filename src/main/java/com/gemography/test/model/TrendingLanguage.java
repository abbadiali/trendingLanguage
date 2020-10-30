package com.gemography.test.model;

import java.util.List;

public class TrendingLanguage {
    private String language;
    private int occurrence;
    private List<String> reposUrls;

    public TrendingLanguage() {
    }

    public TrendingLanguage(String language, int occurrence, List<String> reposUrls) {
        this.language = language;
        this.occurrence = occurrence;
        this.reposUrls = reposUrls;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public List<String> getReposUrls() {
        return reposUrls;
    }

    public void setReposUrls(List<String> reposUrls) {
        this.reposUrls = reposUrls;
    }

    @Override
    public String toString() {
        return "TrendingLanguage{" +
                "language='" + language + '\'' +
                ", occurrence=" + occurrence +
                ", reposUrls=" + reposUrls +
                '}';
    }
}
