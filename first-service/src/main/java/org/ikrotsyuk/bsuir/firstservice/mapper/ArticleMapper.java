package org.ikrotsyuk.bsuir.firstservice.mapper;

import org.ikrotsyuk.bsuir.firstservice.dto.request.ArticleRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.ArticleResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleResponseDTO toDTO(ArticleEntity articleEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "reactions", ignore = true)
    @Mapping(target = "stickers", ignore = true)
    ArticleEntity toEntity(ArticleRequestDTO articleRequestDTO);

    List<ArticleResponseDTO> toDTOList(List<ArticleEntity> articleEntityList);
}
