package org.ikrotsyuk.bsuir.firstservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ikrotsyuk.bsuir.firstservice.dto.request.ArticleRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.ArticleResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.WriterResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.ArticleEntity;
import org.ikrotsyuk.bsuir.firstservice.entity.WriterEntity;
import org.ikrotsyuk.bsuir.firstservice.exception.JSONConverter;
import org.ikrotsyuk.bsuir.firstservice.exception.exceptions.ArticleWithSameTitleFoundException;
import org.ikrotsyuk.bsuir.firstservice.exception.exceptions.NoWriterWithArticleWriterIdFound;
import org.ikrotsyuk.bsuir.firstservice.mapper.ArticleMapper;
import org.ikrotsyuk.bsuir.firstservice.repository.ArticleRepository;
import org.ikrotsyuk.bsuir.firstservice.repository.WriterRepository;
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
    private final WriterRepository writerRepository;
    private final JSONConverter jsonConverter;

    @Autowired
    public ArticleService(ArticleMapper articleMapper, ArticleRepository articleRepository, JSONConverter jsonConverter, WriterRepository writerRepository){
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
        this.jsonConverter = jsonConverter;
        this.writerRepository = writerRepository;
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
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findByTitle(articleRequestDTO.getTitle());
        if(optionalArticleEntity.isEmpty()) {
            Optional<WriterEntity> optionalWriterEntity = writerRepository.findById(articleRequestDTO.getWriterId());
            if(optionalWriterEntity.isPresent()) {
                ArticleEntity articleEntity = articleMapper.toEntity(articleRequestDTO);
                LocalDateTime localDateTime = LocalDateTime.now();
                articleEntity.setCreatedAt(localDateTime);
                articleEntity.setModifiedAt(localDateTime);
                return articleMapper.toDTO(articleRepository.save(articleEntity));
            } else
                try{
                    throw new NoWriterWithArticleWriterIdFound(jsonConverter.convertObjectToJSON(articleRequestDTO));
                }catch (JsonProcessingException ex){
                    return null;
                }

        } else
            try{
                throw new ArticleWithSameTitleFoundException(jsonConverter.convertObjectToJSON(articleRequestDTO));
            }catch (JsonProcessingException ex){
                return articleMapper.toDTO(optionalArticleEntity.get());
            }
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
