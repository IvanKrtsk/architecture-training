package org.ikrotsyuk.bsuir.firstservice.controller;

import org.ikrotsyuk.bsuir.firstservice.dto.response.ArticleResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1.0/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDTO>> getArticles(){
        return new ResponseEntity<>(articleService.getArticles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getArticle(@PathVariable Long id){
        return new ResponseEntity<>(articleService.getArticleById(id), HttpStatus.OK);
    }
}
