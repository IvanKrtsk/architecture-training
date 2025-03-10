package org.ikrotsyuk.bsuir.firstservice.service;

import org.ikrotsyuk.bsuir.firstservice.dto.request.ArticleRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.ArticleResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.WriterResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.ArticleEntity;
import org.ikrotsyuk.bsuir.firstservice.mapper.ArticleMapper;
import org.ikrotsyuk.bsuir.firstservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public ArticleResponseDTO addArticle(ArticleRequestDTO articleRequestDTO){
        ArticleEntity articleEntity = articleMapper.toEntity(articleRequestDTO);
        LocalDateTime localDateTime = LocalDateTime.now();
        articleEntity.setCreatedAt(localDateTime);
        articleEntity.setModifiedAt(localDateTime);
        return articleMapper.toDTO(articleRepository.save(articleEntity));
    }

    public HttpStatus deleteArticle(Long id){
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(id);
        if(optionalArticleEntity.isPresent()){
            articleRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } else
            return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus updateArticle(ArticleResponseDTO articleResponseDTO) {
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleResponseDTO.getId());
        if(optionalArticleEntity.isPresent()){
            ArticleEntity articleEntity = optionalArticleEntity.get();
            articleEntity.getWriter().setId(articleResponseDTO.getWriterId());
            articleEntity.setTitle(articleResponseDTO.getTitle());
            articleEntity.setContent(articleResponseDTO.getContent());
            articleRepository.save(articleEntity);
            return HttpStatus.OK;
        } else
            return HttpStatus.BAD_REQUEST;
    }
}
