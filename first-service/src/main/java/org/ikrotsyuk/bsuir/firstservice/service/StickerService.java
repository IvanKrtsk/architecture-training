package org.ikrotsyuk.bsuir.firstservice.service;

import org.ikrotsyuk.bsuir.firstservice.dto.request.StickerRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.StickerResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.entity.StickerEntity;
import org.ikrotsyuk.bsuir.firstservice.mapper.StickerMapper;
import org.ikrotsyuk.bsuir.firstservice.repository.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StickerService {
    private final StickerMapper stickerMapper;
    private final StickerRepository stickerRepository;

    @Autowired
    public StickerService(StickerMapper stickerMapper, StickerRepository stickerRepository){
        this.stickerMapper = stickerMapper;
        this.stickerRepository = stickerRepository;
    }

    public List<StickerResponseDTO> getStickers(){
        List<StickerEntity> stickerEntityList = stickerRepository.findAll();
        if(stickerEntityList.isEmpty())
            return Collections.emptyList();
        else
            return stickerMapper.toDTOList(stickerEntityList);
    }

    public StickerResponseDTO getSticker(Long id){
        Optional<StickerEntity> optionalStickerEntity = stickerRepository.findById(id);
        return optionalStickerEntity.map(stickerMapper::toDTO).orElse(null);
    }

    public StickerResponseDTO addSticker(StickerRequestDTO stickerRequestDTO){
        StickerEntity stickerEntity = stickerMapper.toEntity(stickerRequestDTO);
        return stickerMapper.toDTO(stickerRepository.save(stickerEntity));
    }

    public HttpStatus deleteSticker(Long id){
        Optional<StickerEntity> optionalStickerEntity = stickerRepository.findById(id);
        if(optionalStickerEntity.isPresent()){
            stickerRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } else
            return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus updateSticker(StickerResponseDTO stickerResponseDTO){
        Optional<StickerEntity> optionalStickerEntity = stickerRepository.findById(stickerResponseDTO.getId());
        if(optionalStickerEntity.isPresent()){
            StickerEntity stickerEntity = optionalStickerEntity.get();
            stickerEntity.setName(stickerResponseDTO.getName());
            stickerRepository.save(stickerEntity);
            return HttpStatus.OK;
        } else
            return HttpStatus.BAD_REQUEST;
    }
}
