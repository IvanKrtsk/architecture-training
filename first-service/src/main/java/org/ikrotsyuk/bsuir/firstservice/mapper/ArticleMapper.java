package org.ikrotsyuk.bsuir.firstservice.mapper;

import org.ikrotsyuk.bsuir.firstservice.dto.request.ArticleRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.ArticleResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.ArticleEntity;
import org.ikrotsyuk.bsuir.firstservice.entity.WriterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(target = "writerId", expression = "java(articleEntity.getWriter().getId())")
    ArticleResponseDTO toDTO(ArticleEntity articleEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writer", expression = "java(mapWriterIdToWriter(articleRequestDTO.getWriterId()))")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "reactions", ignore = true)
    @Mapping(target = "stickers", ignore = true)
    ArticleEntity toEntity(ArticleRequestDTO articleRequestDTO);

    default WriterEntity mapWriterIdToWriter(Long writerId) {
        if (writerId == null) {
            return null;
        }
        WriterEntity writer = new WriterEntity();
        writer.setId(writerId);
        return writer;
    }

    List<ArticleResponseDTO> toDTOList(List<ArticleEntity> articleEntityList);
}
