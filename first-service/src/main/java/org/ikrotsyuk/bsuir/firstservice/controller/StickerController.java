package org.ikrotsyuk.bsuir.firstservice.controller;

import jakarta.validation.Valid;
import org.ikrotsyuk.bsuir.firstservice.dto.request.StickerRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.StickerResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.service.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1.0/stickers")
public class StickerController {
    private final StickerService stickerService;

    @Autowired
    public StickerController(StickerService stickerService){
        this.stickerService = stickerService;
    }

    @GetMapping
    public ResponseEntity<List<StickerResponseDTO>> getStickers(){
        return new ResponseEntity<>(stickerService.getStickers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StickerResponseDTO> getSticker(@PathVariable Long id){
        return new ResponseEntity<>(stickerService.getSticker(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StickerResponseDTO> addSticker(@Valid @RequestBody StickerRequestDTO stickerRequestDTO){
        return new ResponseEntity<>(stickerService.addSticker(stickerRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSticker(@PathVariable Long id){
        return new ResponseEntity<>(stickerService.deleteSticker(id));
    }

    @PutMapping
    public ResponseEntity<?> updateSticker(@Valid @RequestBody StickerResponseDTO stickerResponseDTO){
        HttpStatus status = stickerService.updateSticker(stickerResponseDTO);
        if(status.is2xxSuccessful())
            return new ResponseEntity<>(stickerResponseDTO, status);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
