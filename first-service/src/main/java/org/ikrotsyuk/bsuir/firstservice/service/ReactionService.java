package org.ikrotsyuk.bsuir.firstservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ikrotsyuk.bsuir.firstservice.dto.request.ReactionRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.ReactionResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.ArticleEntity;
import org.ikrotsyuk.bsuir.firstservice.entity.ReactionEntity;
import org.ikrotsyuk.bsuir.firstservice.exception.JSONConverter;
import org.ikrotsyuk.bsuir.firstservice.exception.exceptions.NoArticleWithReactionArticleIdFound;
import org.ikrotsyuk.bsuir.firstservice.mapper.ReactionMapper;
import org.ikrotsyuk.bsuir.firstservice.repository.ArticleRepository;
import org.ikrotsyuk.bsuir.firstservice.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {
    private final ReactionRepository reactionRepository;
    private final ArticleRepository articleRepository;
    private final ReactionMapper reactionMapper;
    private final JSONConverter jsonConverter;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository, ReactionMapper reactionMapper, ArticleRepository articleRepository, JSONConverter jsonConverter){
        this.reactionRepository = reactionRepository;
        this.reactionMapper = reactionMapper;
        this.articleRepository = articleRepository;
        this.jsonConverter = jsonConverter;
    }

    public List<ReactionResponseDTO> getReactions(){
        List<ReactionEntity> reactionEntityList = reactionRepository.findAll();
        if(reactionEntityList.isEmpty())
            return Collections.emptyList();
        else
            return reactionMapper.toDTOList(reactionEntityList);
    }

    public ReactionResponseDTO getReactionById(Long id){
        Optional<ReactionEntity> optionalReactionEntity = reactionRepository.findById(id);
        return optionalReactionEntity.map(reactionMapper::toDTO).orElse(null);
    }

    public ReactionResponseDTO addReaction(ReactionRequestDTO reactionRequestDTO){
        if(articleRepository.existsById(reactionRequestDTO.getArticleId())) {
            ReactionEntity reactionEntity = reactionMapper.toEntity(reactionRequestDTO);
            return reactionMapper.toDTO(reactionRepository.save(reactionEntity));
        } else
            try {
                throw new NoArticleWithReactionArticleIdFound(jsonConverter.convertObjectToJSON(reactionRequestDTO));
            }catch (JsonProcessingException ex){
                return null;
            }
    }

    public HttpStatus deleteReaction(Long id){
        Optional<ReactionEntity> optionalReactionEntity = reactionRepository.findById(id);
        if(optionalReactionEntity.isPresent()) {
            reactionRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } else
            return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus updateReaction(ReactionResponseDTO reactionResponseDTO){
        Optional<ReactionEntity> optionalReactionEntity = reactionRepository.findById(reactionResponseDTO.getId());
        if(optionalReactionEntity.isPresent()){
            ReactionEntity reactionEntity = optionalReactionEntity.get();
            reactionEntity.setContent(reactionResponseDTO.getContent());
            reactionEntity.getArticle().setId(reactionResponseDTO.getArticleId());
            reactionRepository.save(reactionEntity);
            return HttpStatus.OK;
        } else
            return HttpStatus.BAD_REQUEST;
    }
}
