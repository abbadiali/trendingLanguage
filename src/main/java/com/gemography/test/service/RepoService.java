package com.gemography.test.service;

import com.gemography.test.model.TrendingLanguage;

import java.util.List;

public interface RepoService {
    List<TrendingLanguage> getTrendingRepos(String date);
}
