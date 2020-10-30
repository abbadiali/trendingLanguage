package com.gemography.test.controller;

import com.gemography.test.model.TrendingLanguage;
import com.gemography.test.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class RepoController {
    @Autowired
    private RepoService repoService;
    @GetMapping("/display/{date}")
    public ResponseEntity<StringBuilder> displayData(@PathVariable String date){
        List<TrendingLanguage> items =repoService.getTrendingRepos(date);

        StringBuilder sb=new StringBuilder("The languages used by the 100 trending public repos on GitHub.");
        sb.append("\n\n\n\n");
        sb.append(items+"\n\n\n\n\n");
        ResponseEntity<StringBuilder> entity =new ResponseEntity<StringBuilder>(sb, HttpStatus.OK);
        System.out.println(items);
        return entity;
    }
}
