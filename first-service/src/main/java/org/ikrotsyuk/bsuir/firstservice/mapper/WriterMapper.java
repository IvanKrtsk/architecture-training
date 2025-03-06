package org.ikrotsyuk.bsuir.firstservice.mapper;

import org.ikrotsyuk.bsuir.firstservice.dto.request.WriterRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.WriterResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.WriterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WriterMapper {
    WriterResponseDTO toDTO(WriterEntity writerEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "articles", ignore = true)
    WriterEntity toEntity(WriterRequestDTO writerRequestDTO);

    List<WriterResponseDTO> toDTOList(List<WriterEntity> writerEntityList);
}
