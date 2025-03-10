package org.ikrotsyuk.bsuir.firstservice.mapper;

import org.ikrotsyuk.bsuir.firstservice.dto.request.ReactionRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.ReactionResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.ArticleEntity;
import org.ikrotsyuk.bsuir.firstservice.entity.ReactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReactionMapper {
    @Mapping(target = "articleId", expression = "java(reactionEntity.getArticle().getId())")
    ReactionResponseDTO toDTO(ReactionEntity reactionEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "article", expression = "java(mapArticleIdToArticle(reactionRequestDTO.getArticleId()))")
    ReactionEntity toEntity(ReactionRequestDTO reactionRequestDTO);

    default ArticleEntity mapArticleIdToArticle(Long articleId) {
        if(articleId == null){
            return null;
        }
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(articleId);
        return articleEntity;
    }

    List<ReactionResponseDTO> toDTOList(List<ReactionEntity> reactionEntityList);
}
