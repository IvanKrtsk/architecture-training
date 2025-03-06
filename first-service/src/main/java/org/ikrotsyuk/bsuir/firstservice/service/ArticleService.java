package org.ikrotsyuk.bsuir.firstservice.service;

import org.ikrotsyuk.bsuir.firstservice.dto.response.ArticleResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.ArticleEntity;
import org.ikrotsyuk.bsuir.firstservice.mapper.ArticleMapper;
import org.ikrotsyuk.bsuir.firstservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleMapper articleMapper, ArticleRepository articleRepository){
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
    }

    public List<ArticleResponseDTO> getArticles(){
        List<ArticleEntity> articleEntityList = articleRepository.findAll();
        if(articleEntityList.isEmpty())
            return Collections.emptyList();
        else
            return articleMapper.toDTOList(articleEntityList);
    }

    public ArticleResponseDTO getArticleById(Long id){
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(id);
        return optionalArticleEntity.map(articleMapper::toDTO).orElse(null);
    }
}
