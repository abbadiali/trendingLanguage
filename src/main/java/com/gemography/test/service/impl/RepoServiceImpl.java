package com.gemography.test.service.impl;

import com.gemography.test.model.Page;
import com.gemography.test.model.Repository;
import com.gemography.test.model.TrendingLanguage;
import com.gemography.test.service.RepoService;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Component
public class RepoServiceImpl implements RepoService {
    static final String URL_GITHUB_REPOS_PREFIX ="https://api.github.com/search/repositories?q=created:>";
    static final String URL_GITHUB_REPOS_SUFFIX ="&sort=stars&order=desc&page=";

    HashMap<String, ArrayList<String>> items = new HashMap<String, ArrayList<String>>();
    @Override
    public List<TrendingLanguage> getTrendingRepos(String date) {

        Repository[] repositories = get100Repo(date);

        constructListOfTrendingRepos(repositories);


        return constructListOfTrendingLanguages(items);
    }

    private List<TrendingLanguage>  constructListOfTrendingLanguages(HashMap<String, ArrayList<String>> items) {
         List<TrendingLanguage> trendingLanguages = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entry : items.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            trendingLanguages.add(new TrendingLanguage(key,value.size(),value));
        }
        return trendingLanguages;

    }

    private Repository[] get100Repo(String date) {
        List<Repository> repositories = new ArrayList<>();
        int pageNumber=1;
        while (repositories.size()<=100){

        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Page> entity = new HttpEntity<Page>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<Page> response = restTemplate.exchange(URL_GITHUB_REPOS_PREFIX+date+URL_GITHUB_REPOS_SUFFIX+pageNumber, //
                HttpMethod.GET, entity, Page.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);
        for (Repository repository:
                response.getBody().getItems()
             ) {
            if(repository.getLanguage()!=null){
                if(repositories.size()<100) {
                    repositories.add(repository);
                }else
                {
                    return repositories.toArray(new Repository[0]);
                }
            }
        }
            pageNumber++;
        }
        return repositories.toArray(new Repository[0]);
    }

    private void constructListOfTrendingRepos(Repository[] repositories){
        for (Repository e : repositories) {
            if(e.getLanguage()!= null){
                addToList(e.getLanguage(),e.getUrl());
            }
        }
    }

    public synchronized void addToList(String mapKey, String repoLink) {
        List<String> itemsList = items.get(mapKey);

        // if list does not exist create it
        if(itemsList == null) {
            itemsList = new ArrayList<String>();
            itemsList.add(repoLink);
            items.put(mapKey, (ArrayList<String>) itemsList);
        } else {
            // add if item is not already in list
            if(!itemsList.contains(repoLink)) itemsList.add(repoLink);
        }
    }
}
