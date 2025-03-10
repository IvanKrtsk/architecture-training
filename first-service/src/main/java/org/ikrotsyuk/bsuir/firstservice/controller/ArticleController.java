package org.ikrotsyuk.bsuir.firstservice.controller;

import jakarta.validation.Valid;
import org.ikrotsyuk.bsuir.firstservice.dto.request.ArticleRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.ArticleResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ArticleResponseDTO> addArticle(@Valid @RequestBody ArticleRequestDTO articleRequestDTO){
        return new ResponseEntity<>(articleService.addArticle(articleRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id){
        return new ResponseEntity<>(articleService.deleteArticle(id));
    }

    @PutMapping
    public ResponseEntity<?> updateArticle(@Valid @RequestBody ArticleResponseDTO articleResponseDTO){
        HttpStatus status = articleService.updateArticle(articleResponseDTO);
        if(status.is2xxSuccessful())
            return new ResponseEntity<>(articleResponseDTO, status);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
