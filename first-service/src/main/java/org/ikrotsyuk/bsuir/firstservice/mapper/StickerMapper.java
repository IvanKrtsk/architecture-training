package org.ikrotsyuk.bsuir.firstservice.mapper;

import org.ikrotsyuk.bsuir.firstservice.dto.request.StickerRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.StickerResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.StickerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StickerMapper {
    StickerResponseDTO toDTO(StickerEntity stickerEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "articles", ignore = true)
    StickerEntity toEntity(StickerRequestDTO stickerRequestDTO);

    List<StickerResponseDTO> toDTOList(List<StickerEntity> stickerEntityList);
}
